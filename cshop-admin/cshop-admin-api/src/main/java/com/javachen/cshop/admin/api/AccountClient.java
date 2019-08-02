package com.javachen.cshop.admin.api;

import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.common.model.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author june
 * @createTime 2019-08-01 17:13
 * @see
 * @since
 */
@FeignClient(name = "cshop-user-service")
public interface AccountClient {
    @PostMapping(value = "/user/login")
    RestResponse<Admin> processLogin(UserLogin userLogin);

    @PostMapping(value = "/user/logout")
    RestResponse<Admin> processLogout(UserLogin userLogin);

    @GetMapping("/user/{username}")
    RestResponse<Admin> findByUsername(@PathVariable(value = "username") String username);

}
