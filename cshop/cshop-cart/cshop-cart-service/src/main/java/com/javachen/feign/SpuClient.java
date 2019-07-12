package com.javachen.feign;

import com.javachen.api.SpuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SpuClient extends SpuApi {
}
