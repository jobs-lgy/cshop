package com.javachen.cshop.controller;

import com.javachen.cshop.entity.SpuDetail;
import com.javachen.cshop.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpuDetailController {

    @Autowired
    private SpuService spuService;

    /**
     * 按商品ID查询商品描述
     *
     * @param spuId 商品ID
     * @return SpuDetail
     */
    @GetMapping("spuDetail/{spuId}")
    public SpuDetail findSpuDetailById(@PathVariable("spuId") Long spuId) {
        return spuService.findSpuDetailById(spuId);
    }
}
