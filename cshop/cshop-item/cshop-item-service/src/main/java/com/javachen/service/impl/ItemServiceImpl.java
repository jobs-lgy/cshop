package com.javachen.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javachen.common.exception.BusinessException;
import com.javachen.common.exception.ErrorCode;
import com.javachen.common.response.PageResponse;
import com.javachen.common.util.JsonUtils;
import com.javachen.entity.*;
import com.javachen.vo.SpuBo;
import com.javachen.reposity.*;
import com.javachen.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SpuRepository spuRepository;

    @Autowired
    private CategoryReposity categoryReposity;

    @Autowired
    private BrandReposity brandReposity;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SpuDetailRepository spuDetailRepository;

    @Autowired
    private StockRepository stockRepository;

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    public PageResponse<SpuBo> findAllSpuByPage(int page, int rows, String sortBy, Boolean desc, String key, Boolean saleable) {
        Page<Spu> spuPage = null;
        if (StringUtils.isEmpty(key)) {
            spuPage = spuRepository.findAll(new PageRequest(page, rows));
        } else {
            Sort sort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            spuPage = spuRepository.findAllByTitleLikeOrSaleable(key, saleable, new PageRequest(page, rows, sort));
        }

        // 使用spu集合 构造spuBO集合
        List<SpuBo> spuBoList = spuPage.getContent().stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            // 查询分类名称列表并处理成字符串
            Optional<String> categoryNameString = categoryReposity.findAllById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream()
                    .map(Category::getName)
                    .reduce((name1, name2) -> name1 + "/" + name2);
            // 查询品牌名称
            Brand brand = brandReposity.findById(spu.getBrandId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.RRAND_NOT_EXIST));
            // 设置分类以及品牌名称
            spuBo.setBname(brand.getName());
            spuBo.setCname(categoryNameString.get());
            return spuBo;
        }).collect(Collectors.toList());

        return new PageResponse<SpuBo>(spuPage.getTotalElements(), spuPage.getTotalPages(), spuBoList);
    }

    /**
     * 新增商品
     *
     * @param spu 商品信息
     * @return SPU
     */
    @Transactional
    public Spu addSpu(SpuBo spu) {
        spu.setEnable(true);
        spu.setSaleable(true); // 上架
        spuRepository.save(spu);

        // 保存SPU描述
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId()); // 设置ID
        spuDetailRepository.save(spuDetail);

        // 保存SKU列表
        saveSkuAndStock(spu);

        // 发送同步数据消息
//            sendMessage(goods.getId(), Constants.ROUTING_KEY_INSERT_ITEM);

        return spu;
    }

    // 保存SKU列表
    private void saveSkuAndStock(SpuBo goods) {
        goods.getSkuList().forEach(sku -> {
            if (sku.getEnable()) {
                // 保存sku信息
                sku.setSpuId(goods.getId());
                skuRepository.save(sku);
                // 保存库存信息
                Stock stock = new Stock();
                stock.setSkuId(sku.getId());
                stock.setStock(sku.getStock());
                stockRepository.save(stock);
            }
        });
    }

    /**
     * 编辑商品
     *
     * @param spu 商品信息
     * @return SPU
     */
    @Transactional
    public Spu updateSpu(SpuBo spu) {
        // 保存SPU
        spu.setSaleable(true); // 上架
        spu.setEnable(true);
        spuRepository.save(spu);

        // 保存SPU描述
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        spuDetailRepository.save(spuDetail);

        deleteSkuAndStockBySpuId(spu.getId());

        // 保存更新后的数据
        saveSkuAndStock(spu);

        // 发送同步数据消息
//            sendMessage(goods.getId(), Constants.ROUTING_KEY_UPDATE_ITEM);

        return spu;
    }

    private void deleteSkuAndStockBySpuId(Long id) {
        // 保存SKU列表，需要先删除原先的SKU列表
        List<Sku> skuList = skuRepository.findAllBySpuId(id);
        skuRepository.deleteAll(skuList);
        // 删除库存信息
        List<Long> skuIds = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockRepository.findAllBySkuIdIn(skuIds);
        stockRepository.deleteAll(stockList);
    }

    /**
     * 按ID查询商品信息
     *
     * @param spuId spuid
     * @return 商品信息
     */
    public Map<String,Object> findSpuMapById(Long spuId) {
        Spu spu = findSpuById(spuId);
        List<Category> categoryList = categoryReposity.findAllById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        Brand brand = brandReposity.findById(spu.getBrandId())
                .orElseThrow(() -> new BusinessException(ErrorCode.RRAND_NOT_EXIST));

        // 查询商品描述信息
        SpuDetail spuDetail = spuDetailRepository.findById(spuId).orElseThrow(() -> new BusinessException(ErrorCode.SPU_NOT_EXIST));
        // 查询商品SKU列表
        List<Sku> skuList = skuRepository.findAllBySpuId(spuId);

        /**
         * 对于规格属性的处理需要注意以下几点：
         *      1. 所有规格都保存为id和name形式
         *      2. 规格对应的值保存为id和value形式
         *      3. 都是map形式
         *      4. 将特有规格参数单独抽取
         */

        //获取所有规格参数，然后封装成为id和name形式的数据
        String allSpecJson = spuDetail.getSpecifications();
        List<Map<String,Object>> allSpecs = JsonUtils.fromJson(allSpecJson, new TypeReference<List<Map<String, Object>>>() {
        });
        Map<Integer,String> specName = new HashMap<>();
        Map<Integer,Object> specValue = new HashMap<>();
        this.getAllSpecifications(allSpecs,specName,specValue);

        //获取特有规格参数
        String specTJson = spuDetail.getSpecTemplate();
        Map<String,String[]> specs = JsonUtils.fromJson(specTJson, new TypeReference<Map<String, String[]>>() {
        });
        Map<Integer,String> specialParamName = new HashMap<>();
        Map<Integer,String[]> specialParamValue = new HashMap<>();
        this.getSpecialSpec(specs,specName,specValue,specialParamName,specialParamValue);

        //按照组构造规格参数
        List<Map<String,Object>> groups = this.getGroupsSpec(allSpecs,specName,specValue);

        Map<String,Object> map = new HashMap<>();
        map.put("spu",spu);
        map.put("spuDetail",spuDetail);
        map.put("skuList",skuList);
        map.put("brand",brand);
        map.put("categoryList",categoryList);
        map.put("specName",specName);
        map.put("specValue",specValue);
        map.put("groups",groups);
        map.put("specialParamName",specialParamName);
        map.put("specialParamValue",specialParamValue);

        return map;
    }

    @Override
    public SpuBo findSpuById(Long spuId) {
        Spu spu = findSpuById(spuId);
        SpuDetail spuDetail = spuDetailRepository.findById(spuId).orElseThrow(() -> new BusinessException(ErrorCode.SPU_NOT_EXIST));

        List<Sku> skuList = skuRepository.findAllBySpuId(spuId);

        List<Stock> stocks = stockRepository.findAllBySkuIdIn(skuList.stream().map(Sku::getId).collect(Collectors.toList()));

        for (Sku sku:skuList){
            for (Stock stock : stocks){
                if (sku.getId().equals(stock.getSkuId())){
                    sku.setStock(stock.getStock());
                }
            }
        }

        SpuBo spuBo = (SpuBo) spu;
        spuBo.setBrandId(spu.getBrandId());
        spuBo.setSpuDetail(spuDetail);
        spuBo.setSkuList(skuList);
        return spuBo;
    }

    private List<Map<String, Object>> getGroupsSpec(List<Map<String, Object>> allSpecs, Map<Integer, String> specName, Map<Integer, Object> specValue) {
        List<Map<String, Object>> groups = new ArrayList<>();
        int i = 0;
        int j = 0;
        for (Map<String,Object> spec :allSpecs){
            List<Map<String, Object>> params = (List<Map<String, Object>>) spec.get("params");
            List<Map<String,Object>> temp = new ArrayList<>();
            for (Map<String,Object> param :params) {
                for (Map.Entry<Integer, String> entry : specName.entrySet()) {
                    if (entry.getValue().equals(param.get("k").toString())) {
                        String value = specValue.get(entry.getKey()) != null ? specValue.get(entry.getKey()).toString() : "无";
                        Map<String, Object> temp3 = new HashMap<>(16);
                        temp3.put("id", ++j);
                        temp3.put("name", entry.getValue());
                        temp3.put("value", value);
                        temp.add(temp3);
                    }
                }
            }
            Map<String,Object> temp2 = new HashMap<>(16);
            temp2.put("params",temp);
            temp2.put("id",++i);
            temp2.put("name",spec.get("group"));
            groups.add(temp2);
        }
        return groups;
    }

    private void getSpecialSpec(Map<String, String[]> specs, Map<Integer, String> specName, Map<Integer, Object> specValue, Map<Integer, String> specialParamName, Map<Integer, String[]> specialParamValue) {
        if (specs != null) {
            for (Map.Entry<String, String[]> entry : specs.entrySet()) {
                String key = entry.getKey();
                for (Map.Entry<Integer,String> e : specName.entrySet()) {
                    if (e.getValue().equals(key)){
                        specialParamName.put(e.getKey(),e.getValue());
                        //因为是放在数组里面，所以要先去除两个方括号，然后再以逗号分割成数组
                        String  s = specValue.get(e.getKey()).toString();
                        String result = org.apache.commons.lang3.StringUtils.substring(s,1,s.length()-1);
                        specialParamValue.put(e.getKey(), result.split(","));
                    }
                }
            }
        }
    }

    private void getAllSpecifications(List<Map<String, Object>> allSpecs, Map<Integer, String> specName, Map<Integer, Object> specValue) {
        String k = "k";
        String v = "v";
        String unit = "unit";
        String numerical = "numerical";
        String options ="options";
        int i = 0;
        if (allSpecs != null){
            for (Map<String,Object> s : allSpecs){
                List<Map<String, Object>> params = (List<Map<String, Object>>) s.get("params");
                for (Map<String,Object> param :params){
                    String result;
                    if (param.get(v) == null){
                        result = "无";
                    }else{
                        result = param.get(v).toString();
                    }
                    if (param.containsKey(numerical) && (boolean) param.get(numerical)) {
                        if (result.contains(".")){
                            Double d = Double.valueOf(result);
                            if (d.intValue() == d){
                                result = d.intValue()+"";
                            }
                        }
                        i++;
                        specName.put(i,param.get(k).toString());
                        specValue.put(i,result+param.get(unit).toString());
                    } else if (param.containsKey(options)){
                        i++;
                        specName.put(i,param.get(k).toString());
                        specValue.put(i,param.get(options));
                    }else {
                        i++;
                        specName.put(i,param.get(k).toString());
                        specValue.put(i,param.get(v));
                    }
                }
            }
        }
    }

    public SpuDetail findSpuDetailById(Long spuId) {
        return spuDetailRepository.findById(spuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SPU_NOT_EXIST));

    }

    /**
     * 按spuid查询sku列表
     *
     * @param spuId id
     * @return sku列表
     */
    public List<Sku> findAllSkuBySpuId(Long spuId) {
        List<Sku> skuList = skuRepository.findAllBySpuId(spuId);
        // 查询每个sku的库存信息
        List<Stock> stockList = stockRepository.findAllBySkuIdIn(skuList.stream().map(Sku::getId).collect(Collectors.toList()));
        // 将库存数量与skuId生成map
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        // 设置库存数量
        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));

        return skuList;
    }

    public List<Sku> findAllSku() {
        return skuRepository.findAll();

    }

    /**
     * 更新商品状态（上下架）
     *
     * @param spuId spuId
     */
    @Transactional(rollbackFor = Exception.class)
    public void soldOut(Long spuId) {
        Spu oldSpu = this.findSpuById(spuId);
        List<Sku> skuList = this.skuRepository.findAllBySpuId(spuId);
        if (oldSpu.getSaleable()) {
            //下架
            oldSpu.setSaleable(false);
            spuRepository.save(oldSpu);

            //下架sku中的具体商品
            for (Sku sku : skuList) {
                sku.setEnable(false);
                this.skuRepository.save(sku);
            }
        } else {
            //下架
            oldSpu.setSaleable(true);
            spuRepository.save(oldSpu);

            //下架sku中的具体商品
            for (Sku sku : skuList) {
                sku.setEnable(true);
                this.skuRepository.save(sku);
            }
        }
    }

    /**
     * 删除商品
     *
     * @param spuId 商品ID
     * @return 被删除的商品
     */
    @Transactional
    public void deleteSpu(Long spuId) {
        // 删除spu和spuDetail
        spuRepository.deleteById(spuId);
        spuDetailRepository.deleteById(spuId);

        // 删除sku stock列表
        deleteSkuAndStockBySpuId(spuId);

        // 发送同步数据消息
//        sendMessage(spu.getId(), Constants.ROUTING_KEY_DELETE_ITEM);

    }

//    /**
//     * 发送同步数据消息
//     *
//     * @param id         商品ID
//     * @param routingKey rabbitmq routingKey
//     */
//    private void sendMessage(Long id, String routingKey) {
//        // 发送消息
//        try {
//            amqpTemplate.convertAndSend(routingKey, id);
//        } catch (Exception e) {
//            log.error("[{}]商品消息发送异常，商品id：{}", routingKey, id, e);
//        }
//    }

    /**
     * 查询sku信息
     *
     * @param skuId skuId
     * @return Sku 商品sku信息
     */
    public Sku findSkuById(Long skuId) {
        return skuRepository.findById(skuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SKU_NOT_EXIST));
    }
}
