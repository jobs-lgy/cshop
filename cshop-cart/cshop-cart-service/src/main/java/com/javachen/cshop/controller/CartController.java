package com.javachen.cshop.admin.controller;

import com.javachen.cshop.pojo.Cart;
import com.javachen.cshop.admin.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-10-25 20:41
 * @Feature:
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param cart
     * @return
     */
    @PostMapping
    public Cart addCart(@RequestBody Cart cart) {
       return this.cartService.add(cart);
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @GetMapping
    public List<Cart> queryCartList() {
        return this.cartService.findAllCart();
    }

    /**
     * 修改购物车中商品数量
     *
     * @return
     */
    @PutMapping
    public Cart updateNum(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        return this.cartService.updateNum(skuId, num);
    }

    /**
     * 删除购物车中的商品
     *
     * @param skuId
     * @return
     */
    @DeleteMapping("{skuId}")
    public void deleteCart(@PathVariable("skuId") String skuId) {
        this.cartService.delete(skuId);
    }

}
