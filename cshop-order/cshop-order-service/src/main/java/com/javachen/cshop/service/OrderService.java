package com.javachen.cshop.service;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 订单创建
     *
     * @param order
     * @return
     */
    Long add(Order order);

    /**
     * 根据订单号查询订单
     *
     * @param id
     * @return
     */
    Order findById(Long id);

    /**
     * 分页查询用户订单
     *
     * @param page
     * @param rows
     * @param status
     * @return
     */
    PageResponse<Order> findAllByStatus(Integer page, Integer rows, Integer status);

    /**
     * 根据订单号查询商品id
     *
     * @param id
     * @return
     */
    List<Long> findSkuIdsByOrderId(Long id);


    /**
     * 查询库存
     *
     * @param order
     * @return
     */
    List<Long> findStockId(Order order);
}
