package com.javachen.cshop.admin.repository;

import com.javachen.cshop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByUserIdAndStatus(Long userId, Integer status, Pageable page);
}
