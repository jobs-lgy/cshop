package com.javachen.cshop.controller;

import com.javachen.cshop.common.response.CommonResponse;
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
@RequestMapping("address")
@Api("地址管理")
public class OrderAddressController {

    @Autowired
    private OrderAddressService orderAddressService;

    /**
     * 创建收货地址
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "创建收货地址接口", notes = "创建地址")
    @ApiImplicitParam(name = "orderAddress", required = true, value = "地址对象")
    public CommonResponse addAddressByUserId(@RequestBody @Valid OrderAddress orderAddress) {
        this.orderAddressService.add(orderAddress);
        return CommonResponse.success();
    }

    /**
     * 根据用户id查询地址列表
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询收货地址接口，返回地址列表", notes = "查询地址")
    public CommonResponse<List<OrderAddress>> queryAddressByUserId() {
        return CommonResponse.success(this.orderAddressService.findAllByUserId());
    }

    /**
     * 修改收货地址
     *
     * @param orderAddress
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改收货地址接口", notes = "修改地址")
    @ApiImplicitParam(name = "orderAddress", required = true, value = "地址对象")
    public CommonResponse<Void> updateAddressByUserId(@RequestBody OrderAddress orderAddress) {
        this.orderAddressService.update(orderAddress);
        return CommonResponse.success();
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @DeleteMapping("{addressId}")
    @ApiOperation(value = "删除收货地址接口", notes = "创建地址")
    @ApiImplicitParam(name = "addressId", required = true, value = "地址id")
    public CommonResponse<Void> deleteAddress(@PathVariable("addressId") Long addressId) {
        this.orderAddressService.delete(addressId);
        return CommonResponse.success();
    }

    @GetMapping("{addressId}")
    @ApiOperation(value = "根据id查询收货地址接口", notes = "查询地址")
    @ApiImplicitParam(name = "addressId", required = true, value = "地址id")
    public CommonResponse<OrderAddress> queryAddressById(@PathVariable("addressId") Long addressId) {
        return CommonResponse.success(this.orderAddressService.findByIdAndUserId(addressId));
    }
}
