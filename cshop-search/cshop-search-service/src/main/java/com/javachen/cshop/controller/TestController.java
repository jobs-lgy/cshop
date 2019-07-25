package com.javachen.cshop.controller;

import com.javachen.cshop.feign.SpuClient;
import com.javachen.cshop.model.vo.SpuBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private SpuClient spuClient;

    @GetMapping("test")
    public SpuBo index() throws Exception {
        return spuClient.findById(7L).getData();
    }
}
