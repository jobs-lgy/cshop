package com.javachen.cshop.controller;

import com.javachen.cshop.entity.OrderAddress;
import com.javachen.cshop.service.OrderAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api("地址管理")
public class OrderAddressController {

    @Autowired
    private OrderAddressService orderAddressService;

    /**
     * 创建收货地址
     *
     * @return
     */
    @PostMapping("/address")
    @ApiOperation(value = "创建收货地址接口", notes = "创建地址")
    @ApiImplicitParam(name = "orderAddress", required = true, value = "地址对象")
    public OrderAddress addAddressByUserId(@RequestBody @Valid OrderAddress orderAddress) {
        return this.orderAddressService.add(orderAddress);
    }

    /**
     * 根据用户id查询地址列表
     *
     * @return
     */
    @GetMapping("/address")
    @ApiOperation(value = "查询收货地址接口，返回地址列表", notes = "查询地址")
    public List<OrderAddress> queryAddressByUserId() {
        return this.orderAddressService.findAllByUserId();
    }

    /**
     * 修改收货地址
     *
     * @param orderAddress
     * @return
     */
    @PutMapping("/address")
    @ApiOperation(value = "修改收货地址接口", notes = "修改地址")
    @ApiImplicitParam(name = "orderAddress", required = true, value = "地址对象")
    public OrderAddress updateAddressByUserId(@RequestBody OrderAddress orderAddress) {
        return this.orderAddressService.update(orderAddress);
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @DeleteMapping("/address/{id}")
    @ApiOperation(value = "删除收货地址接口", notes = "创建地址")
    @ApiImplicitParam(name = "id", required = true, value = "地址id")
    public void deleteAddress(@PathVariable("id") Long id) {
        this.orderAddressService.delete(id);
    }

    @GetMapping("/address/{id}")
    @ApiOperation(value = "根据id查询收货地址接口", notes = "查询地址")
    @ApiImplicitParam(name = "id", required = true, value = "地址id")
    public OrderAddress queryAddressById(@PathVariable("id") Long id) {
        return this.orderAddressService.findByIdAndUserId(id);
    }
}
