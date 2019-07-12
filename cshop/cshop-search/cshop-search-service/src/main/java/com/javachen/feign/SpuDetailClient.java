package com.javachen.feign;

import com.javachen.api.SpuDetailApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cshop-item-service")
public interface SpuDetailClient extends SpuDetailApi {
}
