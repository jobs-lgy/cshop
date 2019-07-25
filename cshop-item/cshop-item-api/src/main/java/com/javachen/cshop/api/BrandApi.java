package com.javachen.cshop.api;

import com.javachen.cshop.entity.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BrandApi {
    @GetMapping("/brand/ids/{ids}")
    public List<Brand> findAllByIdIn(@PathVariable("ids") List<Long> ids);
}
