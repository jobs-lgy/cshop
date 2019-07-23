package com.javachen.cshop.api;

import com.javachen.cshop.entity.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandApi {
    @GetMapping("brand/list")
    public List<Brand> findAllByIdIn(@RequestParam("ids") List<Long> ids);
}
