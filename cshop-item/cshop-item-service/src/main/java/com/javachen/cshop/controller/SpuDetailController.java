package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.item.entity.SpuDetail;
import com.javachen.cshop.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpuDetailController {

    @Autowired
    private SpuDetailService spuDetailService;

    /**
     * 按商品ID查询商品描述
     *
     * @param spuId 商品ID
     * @return SpuDetail
     */
    @GetMapping("spuDetail/spuId/{spuId}")
    public RestResponse<SpuDetail> findById(@PathVariable("spuId") Long spuId) {
        return RestResponse.success(spuDetailService.findBySpuId(spuId));
    }
}
