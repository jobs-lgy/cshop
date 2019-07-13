package com.javachen.cshop.feign;

import com.javachen.cshop.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cshop-user-service")
public interface UserClient extends UserApi {

}
