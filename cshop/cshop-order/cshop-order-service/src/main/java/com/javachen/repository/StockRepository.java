package com.javachen.repository;


import com.javachen.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock,Long> {


    /**
     * 更新对应商品的库存,且库存必须大于0，否则回滚。
     * @param skuId
     * @param num
     */
    @Query("update Stock a set a.stock = a.stock - ?2 where a.skuId = ?1 and a.stock > 0")
    void reduceStock(Long skuId, Integer num);

    /**
     * 更新对应商品的秒杀库存,且库存必须大于0，否则回滚。
     * @param skuId
     * @param num
     */
    @Query("update Stock a set a.seckillStock = a.seckillStock - ?2 where a.skuId = ?1 and a.seckillStock > 0")
    void reduceSeckStock(Long skuId, Integer num);
}
