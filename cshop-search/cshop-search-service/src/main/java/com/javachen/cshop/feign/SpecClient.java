package com.javachen.cshop.feign;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.item.entity.Spec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cshop-item-service")
public interface SpecClient {
    @GetMapping("/spec")
     RestResponse<Spec> findByCategoryId(@RequestParam("categoryId") Long categoryId);
}