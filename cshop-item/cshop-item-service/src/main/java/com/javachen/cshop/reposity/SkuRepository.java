package com.javachen.cshop.reposity;


import com.javachen.cshop.item.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkuRepository extends JpaRepository<Sku, Long> {
    @Query("select u from Sku u where u.spuId=?1 and u.enable =1")
    List<Sku> findAllBySpuId(Long spuId);


    @Query("select u from Sku u where u.enable =1 ")
    List<Sku> findAll();
}
