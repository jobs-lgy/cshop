package com.javachen.cshop.feign;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.entity.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cshop-item-service")
public interface SkuClient {
    @GetMapping("sku/{id}")
    public RestResponse<Sku> findById(@PathVariable("id") Long id);

    @GetMapping("sku")
    public RestResponse<List<Sku>> findAllBySpuId(@RequestParam("spuId") Long spuId) ;
}