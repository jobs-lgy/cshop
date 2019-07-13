package com.javachen.cshop.feign;

import com.javachen.cshop.api.SpuDetailApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SpuDetailClient extends SpuDetailApi {
}
