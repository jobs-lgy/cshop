package com.javachen.cshop.api;

import com.javachen.cshop.entity.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpuDetailApi {
    @GetMapping("spuDetail/{id}")
    public SpuDetail findById(@PathVariable("id") Long id) ;
}
