package com.javachen.cshop.api;

import com.javachen.cshop.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpecApi {
    /**
     * 查询商品分类对应的规格参数模板
     *
     * @param categoryId
     * @return
     */
    @GetMapping("spec/{categoryId}")
    public CommonResponse<String> querySpecificationByCategoryId(@PathVariable("categoryId") Long categoryId);
}
