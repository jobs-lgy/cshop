package com.javachen.cshop.api;

import com.javachen.cshop.common.model.response.CommonResponse;
import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.model.vo.SpuBo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface SpuApi {

    @GetMapping("spu/page")
    public CommonResponse<PageResponse<SpuBo>> findAllSpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key);

    /**
     * 根据id查询商品
     *
     * @param spuId
     * @return
     */
    @GetMapping("spu/map/{spuId}")
    public CommonResponse<Map<String, Object>> findSpuMapById(@PathVariable("spuId") Long spuId);

    @GetMapping("spu/{spuId}")
    public CommonResponse<SpuBo> findSpuById(@PathVariable("spuId") Long spuId);
}
