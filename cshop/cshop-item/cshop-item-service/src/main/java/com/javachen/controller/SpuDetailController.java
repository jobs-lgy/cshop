package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.entity.SpuDetail;
import com.javachen.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials="true", allowedHeaders = "*")
@RestController
public class SpuDetailController {

    @Autowired
    private ItemService itemService;

    /**
     * 按商品ID查询商品描述
     *
     * @param spuId 商品ID
     * @return SpuDetail
     */
    @GetMapping("spuDetail/{spuId}")
    public CommonResponse<SpuDetail> findSpuDetailById(@PathVariable("spuId") Long spuId) {
        return CommonResponse.success(itemService.findSpuDetailById(spuId));
    }
}
