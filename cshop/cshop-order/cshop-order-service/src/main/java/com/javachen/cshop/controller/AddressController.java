package com.javachen.cshop.controller;

import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.entity.Address;
import com.javachen.cshop.service.AddressService;
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
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 创建收货地址
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "创建收货地址接口", notes = "创建地址")
    @ApiImplicitParam(name = "address", required = true, value = "地址对象")
    public CommonResponse addAddressByUserId(@RequestBody @Valid Address address) {
        this.addressService.add(address);
        return CommonResponse.success();
    }

    /**
     * 根据用户id查询地址列表
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询收货地址接口，返回地址列表", notes = "查询地址")
    public CommonResponse<List<Address>> queryAddressByUserId() {
        return CommonResponse.success(this.addressService.findAllByUserId());
    }

    /**
     * 修改收货地址
     *
     * @param address
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改收货地址接口", notes = "修改地址")
    @ApiImplicitParam(name = "address", required = true, value = "地址对象")
    public CommonResponse<Void> updateAddressByUserId(@RequestBody Address address) {
        this.addressService.update(address);
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
        this.addressService.delete(addressId);
        return CommonResponse.success();
    }

    @GetMapping("{addressId}")
    @ApiOperation(value = "根据id查询收货地址接口", notes = "查询地址")
    @ApiImplicitParam(name = "addressId", required = true, value = "地址id")
    public CommonResponse<Address> queryAddressById(@PathVariable("addressId") Long addressId) {
        return CommonResponse.success(this.addressService.findByIdAndUserId(addressId));
    }
}
