package com.javachen.cshop.feign;

import com.javachen.cshop.entity.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "cshop-item-service")
public interface SpuDetailClient {
    @GetMapping("spuDetail/{id}")
    public SpuDetail findById(@PathVariable("id") Long id) ;
}
