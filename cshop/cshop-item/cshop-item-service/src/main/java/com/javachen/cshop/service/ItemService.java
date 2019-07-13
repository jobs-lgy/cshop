package com.javachen.cshop.service;

import com.javachen.cshop.common.response.PageResponse;
import com.javachen.cshop.entity.Sku;
import com.javachen.cshop.entity.Spu;
import com.javachen.cshop.entity.SpuDetail;
import com.javachen.cshop.model.vo.SpuBo;

import java.util.List;
import java.util.Map;

/**
 * 商品对外接口
 */
public interface ItemService {
    /**
     * 按商品ID查询该商品所有规格的商品列表
     *
     * @param spuId 商品ID
     * @return List<Sku>
     */
    List<Sku> findAllSkuBySpuId(Long spuId);

    List<Sku> findAllSku();

    /**
     * 按商品ID查询商品描述
     *
     * @param spuId 商品ID
     * @return SpuDetail
     */
    SpuDetail findSpuDetailById(Long spuId);

    /**
     * 分页查询商品列表
     *
     * @param page     当前页码
     * @param rows     每页记录数
     * @param sortBy   排序字段
     * @param desc     是否降序
     * @param key      查询关键字
     * @param saleable 是否上架
     * @return List<SpuBo>
     */
    PageResponse<SpuBo> findAllSpuByPage(int page,
                                         int rows,
                                         String sortBy,
                                         Boolean desc,
                                         String key,
                                         Boolean saleable);

    /**
     * 查询spu信息
     *
     * @param spuId 商品ID
     * @return Spu
     */
    Map<String,Object> findSpuMapById(Long spuId);

    SpuBo findSpuById(Long spuId);

    Sku findSkuById(Long skuId);

    void deleteSpu(Long spuId);

    void soldOut(Long spuIds);

    Spu addSpu(SpuBo spuBo);

    Spu updateSpu(SpuBo spuBo);
}
