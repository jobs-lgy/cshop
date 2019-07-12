package com.javachen.feign;

import com.javachen.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cshop-user-service")
public interface UserClient extends UserApi {

}
