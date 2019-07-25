package com.javachen.cshop.controller;

import com.javachen.cshop.entity.Sku;
import com.javachen.cshop.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 按商品ID查询该商品所有规格的商品列表
     *
     * @param spuId 商品ID
     * @return List<Sku>
     */
    @GetMapping("sku")
    public List<Sku> findAllBySpuId(@RequestParam("spuId") Long spuId) {
        if(spuId==null){
            return skuService.findAll();
        }
        return skuService.findAllBySpuId(spuId);
    }

    /**
     * 查询sku信息
     *
     * @param id id
     * @return Sku 商品sku信息
     */
    @GetMapping("sku/{id}")
    public Sku findById(@PathVariable("id") Long id) {
        return skuService.findById(id);
    }
}
