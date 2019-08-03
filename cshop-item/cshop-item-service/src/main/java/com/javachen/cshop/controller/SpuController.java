package com.javachen.cshop.admin.controller;

import com.javachen.cshop.common.domain.response.PageResponse;
import com.javachen.cshop.common.domain.response.RestResponse;
import com.javachen.cshop.entity.Spu;
import com.javachen.cshop.model.vo.SpuBo;
import com.javachen.cshop.admin.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SpuController {
    @Autowired
    private SpuService spuService;

    @GetMapping("/spu")
    public RestResponse<PageResponse<SpuBo>> findAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "rows", defaultValue = "5") int rows,
                                                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                                                @RequestParam(value = "key", required = false) String key,
                                                                @RequestParam(value = "saleable", required = false) Boolean saleable) {
        return RestResponse.success(spuService.findAllByPage(page, rows, sortBy, desc, key, saleable));

    }

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PostMapping("/spu")
    public RestResponse<Spu> add(@RequestBody SpuBo goods) {
        return RestResponse.success(spuService.add(goods));
    }

    /**
     * 编辑商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PutMapping("/spu")
    public RestResponse<Spu> update(@RequestBody SpuBo goods) {
        return RestResponse.success(spuService.update(goods));
    }

    /**
     * 上架下架
     *
     * @return Sku
     */
    @PostMapping("spu/out/{ids}")
    public void soldOut(@PathVariable("ids") List<Long> spuIds) {
        for (Long id : spuIds) {
            this.spuService.soldOut(id);
        }
    }

    /**
     * 删除指定spuId的商品
     *
     * @param spuIds 商品ID
     * @return Spu 被删除的spu
     */
    @DeleteMapping("spu/ids/{ids}")
    public void deleteSpu(@PathVariable("ids") List<Long> spuIds) {
        for (Long id : spuIds) {
            this.spuService.delete(id);
        }
    }

    /**
     * 查询spu详情信息
     *
     * @param spuId 商品ID
     * @return Spu
     */
    @GetMapping("spu/map/{spuId}")
    public RestResponse<Map<String, Object>> findMapById(@PathVariable("spuId") Long spuId) {
        return RestResponse.success(spuService.findMapById(spuId));
    }

    @GetMapping("spu/{id}")
    public RestResponse<SpuBo> findById(@PathVariable("id") Long id) {
        return RestResponse.success(spuService.findById(id));
    }
}
