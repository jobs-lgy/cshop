package com.javachen.cshop.service;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.Spu;
import com.javachen.cshop.entity.SpuDetail;
import com.javachen.cshop.model.vo.SpuBo;

import java.util.Map;

/**
 * 商品对外接口
 */
public interface SpuService {
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
    PageResponse<SpuBo> findAllByPage(int page,
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
    Map<String, Object> findMapById(Long spuId);

    SpuBo findById(Long spuId);

    void delete(Long spuId);

    void soldOut(Long spuIds);

    Spu add(SpuBo spuBo);

    Spu update(SpuBo spuBo);

    SpuDetail findSpuDetailById(Long spuId);
}
