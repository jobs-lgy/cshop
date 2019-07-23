package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.Spu;
import com.javachen.cshop.model.vo.SpuBo;
import com.javachen.cshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SpuController {
    @Autowired
    private ItemService itemService;

    @GetMapping("spu/page")
    public PageResponse<SpuBo> findAllSpuByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "rows", defaultValue = "5") int rows,
                                                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                                                @RequestParam(value = "key", required = false) String key,
                                                                @RequestParam(value = "saleable", required = false) Boolean saleable) {
        return itemService.findAllSpuByPage(page, rows, sortBy, desc, key, saleable);

    }

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PostMapping("spu")
    public Spu addSpu(@RequestBody SpuBo goods) {
        return itemService.addSpu(goods);
    }

    /**
     * 编辑商品
     *
     * @param goods 商品
     * @return Spu
     */
    @PutMapping("spu")
    public Spu updateSpu(@RequestBody SpuBo goods) {
        return itemService.updateSpu(goods);
    }

    /**
     * 上架下架
     *
     * @return Sku
     */
    @PostMapping("spu/out/{ids}")
    public void soldOut(@PathVariable("ids") String spuIds) {
        String separator = "-";
        if (spuIds.contains(separator)) {
            String[] goodsId = spuIds.split(separator);
            for (String id : goodsId) {
                this.itemService.soldOut(Long.parseLong(id));
            }
        } else {
            this.itemService.soldOut(Long.parseLong(spuIds));
        }
    }

    /**
     * 删除指定spuId的商品
     *
     * @param spuIds 商品ID
     * @return Spu 被删除的spu
     */
    @DeleteMapping("spu/{ids}")
    public void deleteSpu(@PathVariable("ids") String spuIds) {
        String separator = "-";
        if (spuIds.contains(separator)) {
            String[] goodsId = spuIds.split(separator);
            for (String id : goodsId) {
                this.itemService.deleteSpu(Long.parseLong(id));
            }
        } else {
            this.itemService.deleteSpu(Long.parseLong(spuIds));
        }
    }

    /**
     * 查询spu详情信息
     *
     * @param spuId 商品ID
     * @return Spu
     */
    @GetMapping("spu/map/{spuId}")
    public Map<String, Object> findSpuMapById(@PathVariable("spuId") Long spuId) {
        return itemService.findSpuMapById(spuId);
    }

    @GetMapping("spu/{spuId}")
    public Spu findSpuById(@PathVariable("spuId") Long spuId) {
        return itemService.findSpuById(spuId);
    }
}
