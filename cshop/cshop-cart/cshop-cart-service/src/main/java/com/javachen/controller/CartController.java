package com.javachen.controller;

import com.javachen.common.exception.BusinessException;
import com.javachen.common.exception.ErrorCode;
import com.javachen.common.response.CommonResponse;
import com.javachen.pojo.Cart;
import com.javachen.service.CartService;
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
     * @param cart
     * @return
     */
    @PostMapping
    public CommonResponse addCart(@RequestBody Cart cart){
        this.cartService.add(cart);
        return CommonResponse.success();
    }

    /**
     * 查询购物车
     * @return
     */
    @GetMapping
    public CommonResponse<List<Cart>> queryCartList(){
        List<Cart> carts = this.cartService.findAllCart();
        if(carts == null){
            throw new BusinessException(ErrorCode.CART_NOT_EXIST);
        }
        return CommonResponse.success(carts);
    }

    /**
     * 修改购物车中商品数量
     * @return
     */
    @PutMapping
    public CommonResponse updateNum(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num){
        this.cartService.updateNum(skuId,num);
        return CommonResponse.success();
    }

    /**
     * 删除购物车中的商品
     * @param skuId
     * @return
     */
    @DeleteMapping("{skuId}")
    public CommonResponse deleteCart(@PathVariable("skuId") String skuId){
        this.cartService.delete(skuId);
        return CommonResponse.success();
    }

}
