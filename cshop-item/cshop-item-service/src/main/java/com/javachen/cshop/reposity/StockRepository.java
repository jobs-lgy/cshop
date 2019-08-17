package com.javachen.cshop.reposity;

import com.javachen.cshop.item.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllBySkuIdIn(List<Long> skuIds);
}
