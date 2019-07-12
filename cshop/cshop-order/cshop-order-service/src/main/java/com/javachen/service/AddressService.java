package com.javachen.service;

import com.javachen.entity.Address;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-10-31 09:43
 * @Feature:
 */
public interface AddressService {
    /**
     * 删除地址
     * @param id
     */
    void delete(Long id);

    /**
     * 更新地址
     * @param address
     */
    void update(Address address);

    /**
     * 查询地址
     * @return
     */
    List<Address> findAllByUserId();

    /**
     * 新增收货地址
     * @param address
     */
    void add(Address address);

    /**
     * 根据地址id查询地址
     * @param id
     * @return
     */
    Address findByIdAndUserId(Long id);
}
