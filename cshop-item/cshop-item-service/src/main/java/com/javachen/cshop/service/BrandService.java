package com.javachen.cshop.service;

import com.javachen.cshop.item.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 品牌对外接口
 */
public interface BrandService {
    /**
     * 查询指定ID的品牌
     *
     * @param id 品牌ID
     * @return Brand
     */
    Brand findById(Long id);

    /**
     * 查询指定ID的品牌
     *
     * @param ids 品牌ID集合
     * @return Brand
     */
    List<Brand> findAllByIdIn(List<Long> ids);

    List<Brand> findAllByCategoryId(Long categoryId);

    Page<Brand> findAllByPage(int page, int rows, String sortBy, Boolean desc, String key);

    Brand add(Brand brand, List<Long> categories);

    Brand update(Brand brand, List<Long> categories);

    void delete(Long id);

    void deleteCategoryBrand(Long brandId);
}
