package com.javachen.cshop.admin.service;

import com.javachen.cshop.entity.Category;

import java.util.List;

/**
 * @author june
 * @createTime 2019-06-17 20:01
 * @see
 * @since
 */
public interface CategoryService {
    // 分类一级菜单的父ID
    public static final long FIRST_CATEGORY_PARENT_ID = 0;

    List<Category> findAllByParentId(Long parentId);

    Category findTop();

    List<Category> findAllByIdIn(List<Long> ids);

    Category findByBrandyId(Long brandId);

    Category findById(Long categoryId);

    Category update(Category category);

    Category add(Category category);

    void delete(Long categoryId);

    public List<Category> findAllByCid3(Long id);
}
