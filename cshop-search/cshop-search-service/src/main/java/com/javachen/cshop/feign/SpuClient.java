package com.javachen.cshop.feign;

import com.javachen.cshop.common.model.response.PagedResult;
import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.item.model.vo.SpuBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "cshop-item-service")
public interface SpuClient {

    @GetMapping("/spu")
    RestResponse<PagedResult<SpuBo>> findAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "sortBy", required = false) String sortBy,
                                                   @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                                   @RequestParam(value = "key", required = false) String key,
                                                   @RequestParam(value = "saleable", required = false) Boolean saleable);

    /**
     * 根据id查询商品
     *
     * @param spuId
     * @return
     */
    @GetMapping("spu/map/{spuId}")
    public Map<String, Object> findMapById(@PathVariable("spuId") Long spuId);

    @GetMapping("spu/{id}")
    public SpuBo findById(@PathVariable("id") Long id);
}
