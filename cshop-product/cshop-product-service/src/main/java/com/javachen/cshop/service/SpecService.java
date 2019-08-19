package com.javachen.cshop.service;

import com.javachen.cshop.item.entity.Spec;

/**
 * 规格对外接口
 */
public interface SpecService {
    /**
     * 查询指定分类的规格数据
     *
     * @param categoryId 分类ID
     * @return Specification
     */
    Spec findByCategoryId(Long categoryId);

    Spec add(Spec spec);

    Spec update(Spec spec);

    void delete(Long id);

//    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic);
}
