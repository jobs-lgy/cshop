package com.javachen.cshop.api;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cshop-item-service")
public interface CategoryClient {
    @GetMapping("/category/ids")
    public RestResponse<List<Category>> findAllByIdIn(@RequestParam("ids") List<Long> ids);
}
