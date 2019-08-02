package com.javachen.cshop.admin.repository;

import com.javachen.cshop.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {
    @Query("delete from OrderAddress a where a.id=?1 and a.userId=?2")
    void deleteByIdAndUserId(Long id, Long userId);

    List<OrderAddress> findAllByUserId(Long userId);

    OrderAddress findByIdAndUserId(Long id, Long userId);
}
