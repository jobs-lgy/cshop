package com.javachen.cshop.auth.feign;

import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.common.model.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author june
 * @createTime 2019-08-01 17:13
 * @see
 * @since
 */
@FeignClient(name = "cshop-admin-service")
public interface AccountClient {
    @GetMapping("/user/username/{username}")
    RestResponse<Admin> findByUsername(@PathVariable(value = "username") String username);
}
