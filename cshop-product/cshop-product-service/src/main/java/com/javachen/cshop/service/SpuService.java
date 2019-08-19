package com.javachen.cshop.service;

import com.javachen.cshop.common.model.response.PagedResult;
import com.javachen.cshop.item.entity.Spu;
import com.javachen.cshop.item.model.vo.SpuBo;

import java.util.Map;

/**
 * 商品对外接口
 */
public interface SpuService {
    /**
     * 分页查询商品列表
     *
     * @param page     当前页码
     * @param size     每页记录数
     * @param sortBy   排序字段
     * @param desc     是否降序
     * @param key      查询关键字
     * @param saleable 是否上架
     * @return List<SpuBo>
     */
    PagedResult<SpuBo> findAllByPage(int page,
                                     int size,
                                     String sortBy,
                                     Boolean desc,
                                     String key,
                                     Boolean saleable);

    /**
     * 查询spu信息
     *
     * @param id 商品ID
     * @return Spu
     */
    Map<String, Object> findMapById(Long id);

    SpuBo findById(Long id);

    void delete(Long id);

    void soldOut(Long id);

    Spu add(SpuBo spuBo);

    Spu update(SpuBo spuBo);
}
