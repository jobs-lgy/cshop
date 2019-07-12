package com.javachen.api;

import com.javachen.common.response.CommonResponse;
import com.javachen.entity.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandApi {
    @GetMapping("brand/list")
    public CommonResponse<List<Brand>> findAllByIdIn(@RequestParam("ids") List<Long> ids);
}
