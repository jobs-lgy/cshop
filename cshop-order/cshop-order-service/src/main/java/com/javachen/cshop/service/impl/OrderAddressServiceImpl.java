package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.entity.OrderAddress;
import com.javachen.cshop.admin.repository.OrderAddressRepository;
import com.javachen.cshop.admin.service.OrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    private OrderAddressRepository orderAddressRepository;

    @Override
    public void delete(Long id) {
//        this.orderAddressRepository.deleteByIdAndUserId(id, userResponse.getId());
    }

    @Override
    public OrderAddress update(OrderAddress orderAddress) {
//        orderAddress.setUserId(userResponse.getId());
        setDefaultAddress(orderAddress);
        return this.orderAddressRepository.save(orderAddress);

    }

    @Override
    public List<OrderAddress> findAllByUserId() {
//        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.orderAddressRepository.findAllByUserId(1L);
    }

    @Override
    public OrderAddress add(OrderAddress orderAddress) {
//        AuthUser userResponse = LoginInterceptor.getLoginUser();
//        orderAddress.setUserId(userResponse.getId());
        setDefaultAddress(orderAddress);
        return this.orderAddressRepository.save(orderAddress);
    }

    @Override
    public OrderAddress findByIdAndUserId(Long addressId) {
//        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.orderAddressRepository.findByIdAndUserId(addressId, 1L);
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
