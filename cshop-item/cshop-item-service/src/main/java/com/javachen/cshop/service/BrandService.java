package com.javachen.cshop.service;

import com.javachen.cshop.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 品牌对外接口
 */
public interface BrandService {
    /**
     * 查询指定ID的品牌
     *
     * @param brandId 品牌ID
     * @return Brand
     */
    @GetMapping("brand/{brandId}")
    Brand findById(@PathVariable("brandId") Long brandId);

    /**
     * 查询指定ID的品牌
     *
     * @param ids 品牌ID集合
     * @return Brand
     */
    @GetMapping("brand/ids")
    List<Brand> findAllByIdIn(@RequestParam("ids") List<Long> ids);

    List<Brand> findAllByCategoryId(Long categoryId);

    Page<Brand> findAllByPage(int page, int rows, String sortBy, Boolean desc, String key);

    Brand add(Brand brand, List<Long> categories);

    Brand update(Brand brand, List<Long> categories);

    void deleteBrand(Long brandId);

    void deleteCategoryBrand(Long brandId);
}
