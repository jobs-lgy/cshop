package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.entity.Sku;
import com.javachen.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials="true", allowedHeaders = "*")
@RestController
public class SkuController {

    @Autowired
    private ItemService itemService;

    /**
     * 按商品ID查询该商品所有规格的商品列表
     *
     * @param spuId 商品ID
     * @return List<Sku>
     */
    @GetMapping("sku/spu/{spuId}")
    public CommonResponse<List<Sku>> findAllSkuBySpuId(@PathVariable("spuId") Long spuId) {
        return CommonResponse.success(itemService.findAllSkuBySpuId(spuId));
    }

    /**
     * 查询sku信息
     *
     * @param skuId skuId
     * @return Sku 商品sku信息
     */
    @GetMapping("sku/{skuId}")
    public CommonResponse<Sku> findSkuById(@PathVariable("skuId") Long skuId) {
        return CommonResponse.success(itemService.findSkuById(skuId));
    }

    @GetMapping("sku")
    public CommonResponse<List<Sku>> findAllSku() {
        //FIXME
        return CommonResponse.success(itemService.findAllSkuBySpuId(76l));
    }
}
