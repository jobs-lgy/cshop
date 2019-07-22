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
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> findAllByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == -1) {
            return Arrays.asList(this.categoryService.findTop());
        }
        return this.categoryService.findAllByParentId(pid);
    }

    /**
     * 新增分类
     *
     * @param category 分类
     * @return Category
     */
    @PostMapping()
    public Category add(Category category) {
        return categoryService.add(category);
    }

    /**
     * 更新分类
     *
     * @param category 分类
     * @return Category
     */
    @PutMapping()
    public Category update(Category category) {
        return categoryService.update(category);
    }

    /**
     * 删除指定ID的分类
     *
     * @param categoryId 分类ID
     * @return Category 被删除的分类对象
     */
    @DeleteMapping("/{categoryId}")
    public Boolean delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return true;
    }

    /**
     * 根据商品分类id查询分类集合
     *
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("/all")
    public List<Category> findAllByIdIn(@RequestParam("ids") List<Long> ids) {
        return categoryService.findAllByIdIn(ids);
    }

    /**
     * 根据商品分类id查询名称
     *
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("/names")
    public List<String> findAllNameByIdIn(@RequestParam("ids") List<Long> ids) {
        return this.categoryService.findAllByIdIn(ids).stream().map(Category::getName).collect(Collectors.toList());
    }

    @GetMapping("/bid/{brandId}")
    public Category findByBrandyId(@PathVariable("brandId") Long brandId) {
        return categoryService.findByBrandyId(brandId);
    }

    /**
     * 根据分类id集合查询分类名称
     *
     * @param id
     * @return
     */
    @GetMapping("list/level/{cid3}")
    public List<Category> findAllByCid3(@PathVariable("cid3") Long id) {
        return categoryService.findAllByCid3(id);
    }
}