package com.javachen.cshop.service;

import com.javachen.cshop.entity.Specification;

/**
 * 规格对外接口
 */
public interface SpecificationService {
    /**
     * 查询指定分类的规格数据
     *
     * @param categoryId 分类ID
     * @return Specification
     */
    Specification findByCategoryId(Long categoryId);

    Specification add(Specification specification);

    Specification update(Specification specification);

    void delete(Long id);

//    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic);
}
