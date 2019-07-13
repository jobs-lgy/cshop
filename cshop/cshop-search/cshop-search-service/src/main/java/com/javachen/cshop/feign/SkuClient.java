package com.javachen.cshop.feign;

import com.javachen.cshop.api.SkuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SkuClient extends SkuApi {
}
