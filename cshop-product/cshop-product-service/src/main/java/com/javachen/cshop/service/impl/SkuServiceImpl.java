package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.exception.CustomException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.item.entity.Sku;
import com.javachen.cshop.item.entity.Stock;
import com.javachen.cshop.reposity.SkuRepository;
import com.javachen.cshop.reposity.StockRepository;
import com.javachen.cshop.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private StockRepository stockRepository;

    /**
     * 按spuid查询sku列表
     *
     * @param spuId id
     * @return sku列表
     */
    public List<Sku> findAllBySpuId(Long spuId) {
        List<Sku> skuList = skuRepository.findAllBySpuId(spuId);
        // 查询每个sku的库存信息
        List<Stock> stockList = stockRepository.findAllBySkuIdIn(skuList.stream().map(Sku::getId).collect(Collectors.toList()));
        // 将库存数量与skuId生成map
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        // 设置库存数量
//        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));

        return skuList;
    }

    public List<Sku> findAll() {
        return skuRepository.findAll();

    }

    /**
     * 查询sku信息
     *
     * @param id id
     * @return Sku 商品sku信息
     */
    public Sku findById(Long id) {
        return skuRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SKU_NOT_EXIST));
    }
}
