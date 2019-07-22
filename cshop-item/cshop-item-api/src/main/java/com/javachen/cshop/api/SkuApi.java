package com.javachen.cshop.api;

import com.javachen.cshop.common.model.response.CommonResponse;
import com.javachen.cshop.entity.Sku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SkuApi {
    /**
     * 根据sku的id查询sku
     *
     * @param skuId
     * @return
     */
    @GetMapping("sku/{skuId}")
    public CommonResponse<Sku> findSkuById(@PathVariable("skuId") Long skuId);

    /**
     * 根据Spu的id查询其下所有的sku
     *
     * @param spuId
     * @return
     */
    @GetMapping("sku/spu/{spuId}")
    public CommonResponse<List<Sku>> findAllSkuBySpuId(@PathVariable("spuId") Long spuId);
}