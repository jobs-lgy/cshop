package com.javachen.cshop.api;

import com.javachen.cshop.entity.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SpecApi {
    @GetMapping("/spec")
    public Specification findByCategoryId(@RequestParam("categoryId") Long categoryId);
}
