package com.javachen.service.impl;

import com.javachen.common.auth.AuthUser;
import com.javachen.entity.Address;
import com.javachen.common.web.LoginInterceptor;
import com.javachen.repository.AddressRepository;
import com.javachen.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void delete(Long id) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        this.addressRepository.deleteByIdAndUserId(id, userResponse.getId());
    }

    @Override
    public void update(Address address) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        address.setUserId(userResponse.getId());
        setDefaultAddress(address);
        this.addressRepository.save(address);

    }

    @Override
    public List<Address> findAllByUserId() {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.addressRepository.findAllByUserId(userResponse.getId());
    }

    @Override
    public void add(Address address) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        address.setUserId(userResponse.getId());
        setDefaultAddress(address);
        this.addressRepository.save(address);
    }

    @Override
    public Address findByIdAndUserId(Long addressId) {
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        return this.addressRepository.findByIdAndUserId(addressId, userResponse.getId());
    }

    public void setDefaultAddress(Address address) {
        if (address.getIsDefault()) {
            //如果将本地址设置为默认地址，那么该用户下的其他地址都应该是非默认地址
            List<Address> addressList = this.findAllByUserId();
            addressList.forEach(addressTemp -> {
                if (addressTemp.getIsDefault()) {
                    addressTemp.setIsDefault(false);
                    this.addressRepository.save(addressTemp);
                }
            });
        }
    }
}
