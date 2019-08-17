package com.javachen.cshop.feign;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.item.entity.Category;
import com.javachen.cshop.item.entity.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cshop-item-service")
public interface SpuDetailClient {
    @GetMapping("spuDetail/spuId/{spuId}")
    public RestResponse<SpuDetail> findById(@PathVariable("spuId") Long spuId);
}