package com.javachen.cshop.feign;

import com.javachen.cshop.api.SpuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SpuClient extends SpuApi {
}
