package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.web.LoginInterceptor;
import com.javachen.cshop.entity.OrderAddress;
import com.javachen.cshop.repository.OrderAddressRepository;
import com.javachen.cshop.service.OrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    private OrderAddressRepository orderAddressRepository;

    @Override
    public void delete(Long id) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        this.orderAddressRepository.deleteByIdAndUserId(id, userResponse.getId());
    }

    @Override
    public OrderAddress update(OrderAddress orderAddress) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        orderAddress.setUserId(userResponse.getId());
        setDefaultAddress(orderAddress);
        return this.orderAddressRepository.save(orderAddress);

    }

    @Override
    public List<OrderAddress> findAllByUserId() {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.orderAddressRepository.findAllByUserId(userResponse.getId());
    }

    @Override
    public OrderAddress add(OrderAddress orderAddress) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        orderAddress.setUserId(userResponse.getId());
        setDefaultAddress(orderAddress);
        return this.orderAddressRepository.save(orderAddress);
    }

    @Override
    public OrderAddress findByIdAndUserId(Long addressId) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.orderAddressRepository.findByIdAndUserId(addressId, userResponse.getId());
    }

    public void setDefaultAddress(OrderAddress orderAddress) {
        if (orderAddress.getIsDefault()) {
            //如果将本地址设置为默认地址，那么该用户下的其他地址都应该是非默认地址
            List<OrderAddress> orderAddressList = this.findAllByUserId();
            orderAddressList.forEach(orderAddressTemp -> {
                if (orderAddressTemp.getIsDefault()) {
                    orderAddressTemp.setIsDefault(false);
                    this.orderAddressRepository.save(orderAddressTemp);
                }
            });
        }
    }
}
