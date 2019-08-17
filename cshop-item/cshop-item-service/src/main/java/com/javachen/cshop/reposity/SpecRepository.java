package com.javachen.cshop.reposity;


import com.javachen.cshop.item.entity.Spec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecRepository extends JpaRepository<Spec, Long> {
    Spec findByCategoryId(Long categoryId);
}
