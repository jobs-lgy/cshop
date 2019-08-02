package com.javachen.cshop.auth.feign;

import com.javachen.cshop.admin.entity.AdminUser;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.vo.UserWithPassword;
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
    RestResponse<AdminUser> processLogin(UserLogin userLogin);

    @PostMapping(value = "/user/logout")
    RestResponse<AdminUser> processLogout(UserLogin userLogin);

    @GetMapping("/user/account/{username}")
    RestResponse<UserWithPassword> findByUsername(@PathVariable(value = "username") String username);
}
