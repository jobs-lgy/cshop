package com.javachen.cshop.service;

import com.javachen.cshop.entity.OrderAddress;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-10-31 09:43
 * @Feature:
 */
public interface OrderAddressService {
    /**
     * 删除地址
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新地址
     *
     * @param orderAddress
     */
    void update(OrderAddress orderAddress);

    /**
     * 查询地址
     *
     * @return
     */
    List<OrderAddress> findAllByUserId();

    /**
     * 新增收货地址
     *
     * @param orderAddress
     */
    void add(OrderAddress orderAddress);

    /**
     * 根据地址id查询地址
     *
     * @param id
     * @return
     */
    OrderAddress findByIdAndUserId(Long id);
}
