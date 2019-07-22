package com.javachen.cshop.reposity;

import com.javachen.cshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author june
 * @createTime 2019-06-17 20:03
 * @see
 * @since
 */
public interface CategoryReposity extends JpaRepository<Category, Long> {
    List<Category> findAllByParentId(Long parentId);

    List<Category> findAllByIdIn(List<Long> ids);

    @Query("SELECT c FROM CategoryBrand cb, Category c WHERE cb.brandId = ?1 AND cb.categoryId = c.id")
    Category findByBrandId(Long brandId);


    @Query("SELECT c FROM Category c WHERE c.id = (SELECT MAX(id) FROM Category)")
    Category findTop();
}
