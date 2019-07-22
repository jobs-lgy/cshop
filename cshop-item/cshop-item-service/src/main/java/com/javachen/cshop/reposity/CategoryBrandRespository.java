package com.javachen.cshop.reposity;

import com.javachen.cshop.entity.CategoryBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author june
 * @createTime 2019-06-17 23:33
 * @see
 * @since
 */
public interface CategoryBrandRespository extends JpaRepository<CategoryBrand, CategoryBrand.CategoryBrandPk> {
    @Modifying
    @Query("delete from CategoryBrand cb where cb.brandId=?1")
    int deleteByBrandId(Long brandId);

    @Modifying
    @Query("delete from CategoryBrand cb where cb.categoryId=?1")
    int deleteByCategoryId(Long categoryId);
}
