package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.entity.Category;
import com.javachen.cshop.reposity.CategoryBrandRespository;
import com.javachen.cshop.reposity.CategoryReposity;
import com.javachen.cshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author june
 * @createTime 2019-06-17 20:00
 * @see
 * @since
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryReposity categoryReposity;

    @Autowired
    private CategoryBrandRespository categoryBrandRespository;

    /**
     * 根据parentId查询子类目
     *
     * @param parentId
     * @return
     */
    public List<Category> findAllByParentId(Long parentId) {
        return this.categoryReposity.findAllByParentId(parentId);
    }

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 分类
     */
    public Category add(Category category) {
        // 判断其父节点是否为父节点
        if (category.getParentId() != FIRST_CATEGORY_PARENT_ID) {
            Category pCategory = findById(category.getParentId());
            // 判断是否已经是父菜单
            if (!pCategory.getIsParent()) {// 不为父菜单
                // 设置为父菜单并保存
                pCategory.setIsParent(true);
                categoryReposity.save(pCategory);
            }
        } else {
            categoryReposity.save(category);
        }

        return category;
    }

    public Category findById(Long id) {
        return categoryReposity.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_EXIST));

    }

    /**
     * 编辑分类
     *
     * @param category 分类
     * @return 分类
     */
    public Category update(Category category) {
        categoryReposity.save(category);
        return category;
    }

    /**
     * 删除分类
     *
     * @param categoryId 分类id
     */
    public void delete(Long categoryId) {
        Category category = findById(categoryId);
        // 判断该分类是否为父分类，父分类不能删除！！
        if (category.getIsParent()) {
            if (categoryReposity.findAllByParentId(categoryId).size() > 0) {
                throw new BusinessException(ErrorCode.CATEGORY_PARENT_ID_EXIST);
            }
        } else {
            //1.查询此节点的父亲节点的孩子个数 ===> 查询还有几个兄弟
            List<Category> brothers = categoryReposity.findAllByParentId(category.getParentId());
            if (brothers.size() > 1) {
                //有兄弟,直接删除自己
                categoryReposity.deleteById(categoryId);
            } else {
                //没有兄弟了，更新父节点
                Category parent = new Category();
                parent.setId(category.getParentId());
                parent.setIsParent(false);
                categoryReposity.save(category);
            }
            //删除关联表
            categoryBrandRespository.deleteByCategoryId(categoryId);
        }

    }

    public List<Category> findAllByIdIn(List<Long> ids) {
        return categoryReposity.findAllByIdIn(ids);
    }

    public Category findTop() {
        return categoryReposity.findTop();
    }

    @Override
    public Category findByBrandyId(Long brandId) {
        return categoryReposity.findByBrandId(brandId);
    }

    @Override
    public List<Category> findAllByCid3(Long id) {
        List<Category> categoryList = new ArrayList<>();
        Category category = this.findById(id);
        while (category.getParentId() != 0) {
            categoryList.add(category);
            category = this.findById(category.getParentId());
        }
        categoryList.add(category);
        return categoryList;
    }
}