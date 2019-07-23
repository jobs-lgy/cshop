package com.javachen.cshop.controller;

import com.javachen.cshop.entity.Sku;
import com.javachen.cshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
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
    public List<Sku> findAllSkuBySpuId(@PathVariable("spuId") Long spuId) {
        return itemService.findAllSkuBySpuId(spuId);
    }

    /**
     * 查询sku信息
     *
     * @param skuId skuId
     * @return Sku 商品sku信息
     */
    @GetMapping("sku/{skuId}")
    public Sku findSkuById(@PathVariable("skuId") Long skuId) {
        return itemService.findSkuById(skuId);
    }

    @GetMapping("sku")
    public List<Sku> findAllSku() {
        //FIXME
        return itemService.findAllSkuBySpuId(76l);
    }
}
