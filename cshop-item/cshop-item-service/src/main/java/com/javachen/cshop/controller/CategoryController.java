package com.javachen.cshop.controller;

import com.javachen.cshop.entity.Category;
import com.javachen.cshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author june
 * @createTime 2019-06-17 19:59
 * @see
 * @since
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询子子分类
     *
     * @param id
     * @return
     */
    @GetMapping("/category/children/{id}")
    public List<Category> findAllByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        if (id == -1) {
            return Arrays.asList(this.categoryService.findTop());
        }
        return this.categoryService.findAllByParentId(id);
    }

    /**
     * 根据cid3查询分类
     *
     * @param id
     * @return
     */
    @GetMapping("category/cid3/{cid3}")
    public List<Category> findAllByCid3(@PathVariable("cid3") Long id) {
        return categoryService.findAllByCid3(id);
    }


    /**
     * 根据商品分类id查询分类集合
     *
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("/category/ids")
    public List<Category> findAllByIdIn(@RequestParam("ids") List<Long> ids) {
        return categoryService.findAllByIdIn(ids);
    }

    @GetMapping("/category/brand/{brandId}")
    public Category findByBrandyId(@PathVariable("brandId") Long brandId) {
        return categoryService.findByBrandyId(brandId);
    }

    /**
     * 新增分类
     *
     * @param category 分类
     * @return Category
     */
    @PostMapping("/category")
    public Category add(Category category) {
        return categoryService.add(category);
    }

    /**
     * 更新分类
     *
     * @param category 分类
     * @return Category
     */
    @PutMapping("/category")
    public Category update(Category category) {
        return categoryService.update(category);
    }

    /**
     * 删除指定ID的分类
     *
     * @param id 分类ID
     * @return Category 被删除的分类对象
     */
    @DeleteMapping("/category/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return true;
    }

}