package com.javachen.feign;

import com.javachen.api.SkuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SkuClient extends SkuApi {
}
