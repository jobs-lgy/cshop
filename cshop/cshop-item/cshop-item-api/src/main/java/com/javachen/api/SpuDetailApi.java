package com.javachen.api;

import com.javachen.common.response.CommonResponse;
import com.javachen.entity.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpuDetailApi {
    @GetMapping("spuDetail/{spuId}")
    public CommonResponse<SpuDetail> findSpuDetailById(@PathVariable("spuId") Long spuId);
}
