package com.javachen.cshop.service;

import com.javachen.cshop.pojo.Cart;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-10-25 20:47
 * @Feature:
 */
public interface CartService {
    /**
     * 添加购物车
     *
     * @param cart
     */
    void add(Cart cart);

    /**
     * 查询购物车
     *
     * @return
     */
    List<Cart> findAllCart();

    /**
     * 更新购物车中商品数量
     *
     * @param skuId
     * @param num
     */
    void updateNum(Long skuId, Integer num);

    /**
     * 删除购物车中的商品
     *
     * @param skuId
     */
    void delete(String skuId);
}