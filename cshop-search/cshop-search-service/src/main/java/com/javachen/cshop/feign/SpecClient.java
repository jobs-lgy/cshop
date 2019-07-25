package com.javachen.cshop.feign;

import com.javachen.cshop.entity.Specification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cshop-item-service")
public interface SpecClient {
    @GetMapping("/spec")
    public Specification findByCategoryId(@RequestParam("categoryId") Long categoryId);
}
