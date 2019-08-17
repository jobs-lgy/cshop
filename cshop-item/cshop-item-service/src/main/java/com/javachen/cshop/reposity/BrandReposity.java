package com.javachen.cshop.reposity;

import com.javachen.cshop.item.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandReposity extends JpaRepository<Brand, Long> {

    Page<Brand> findAll(Pageable pageable);

    Page<Brand> findAllByNameLike(String name, Pageable pageable);

    @Query("SELECT b FROM CategoryBrand cb, Brand b WHERE cb.id.categoryId = ?1 AND cb.id.brandId = b.id")
    List<Brand> findAllByCategoryId(Long categoryId);

    List<Brand> findAllByIdIn(List<Long> ids);
}
