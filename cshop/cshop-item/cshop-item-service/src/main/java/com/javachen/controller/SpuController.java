package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.common.response.PageResponse;
import com.javachen.entity.Spu;
import com.javachen.model.vo.SpuBo;
import com.javachen.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(allowCredentials="true", allowedHeaders = "*")
@RestController
public class SpuController {
    @Autowired
    private ItemService itemService;

    @GetMapping("spu/page")
    public CommonResponse<PageResponse<SpuBo>> findAllSpuByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "rows", defaultValue = "5") int rows,
                                                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                                                @RequestParam(value = "key", required = false) String key,
                                                                @RequestParam(value = "saleable", required = false) Boolean saleable) {
        return CommonResponse.success(itemService.findAllSpuByPage(page, rows, sortBy, desc, key, saleable));

    }

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PostMapping("spu")
    public CommonResponse addSpu(@RequestBody SpuBo goods) {
         itemService.addSpu(goods);
        return CommonResponse.success();
    }

    /**
     * 编辑商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PutMapping("spu")
    public CommonResponse updateSpu(@RequestBody SpuBo goods) {
        itemService.updateSpu(goods);
        return CommonResponse.success();
    }

    /**
     * 上架下架
     *
     * @return Sku
     */
    @GetMapping("spu/out/{ids}")
    public CommonResponse soldOut(@PathVariable("ids") String spuIds) {
        String separator = "-";
        if (spuIds.contains(separator)) {
            String[] goodsId = spuIds.split(separator);
            for (String id : goodsId) {
                this.itemService.soldOut(Long.parseLong(id));
            }
        } else {
            this.itemService.soldOut(Long.parseLong(spuIds));
        }
        return CommonResponse.success();
    }

    /**
     * 删除指定spuId的商品
     *
     * @param spuIds 商品ID
     * @return Spu 被删除的spu
     */
    @DeleteMapping("spu/{ids}")
    public CommonResponse deleteSpu(@PathVariable("ids") String spuIds) {
        String separator = "-";
        if (spuIds.contains(separator)) {
            String[] goodsId = spuIds.split(separator);
            for (String id : goodsId) {
                this.itemService.deleteSpu(Long.parseLong(id));
            }
        } else {
            this.itemService.deleteSpu(Long.parseLong(spuIds));
        }
        return CommonResponse.success();
    }

    /**
     * 查询spu详情信息
     *
     * @param spuId 商品ID
     * @return Spu
     */
    @GetMapping("spu/map/{spuId}")
    public CommonResponse<Map<String,Object>> findSpuMapById(@PathVariable("spuId") Long spuId) {
        return CommonResponse.success(itemService.findSpuMapById(spuId));
    }

    @GetMapping("spu/{spuId}")
    public CommonResponse<Spu> findSpuById(@PathVariable("spuId") Long spuId) {
        return CommonResponse.success(itemService.findSpuById(spuId));
    }
}
