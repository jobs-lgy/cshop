package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.Brand;
import com.javachen.cshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    public PageResponse<Brand> queryBrandByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "rows", defaultValue = "5") int rows,
                                                                @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                                @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                @RequestParam(value = "key", required = false) String key) {
        Page<Brand> result = brandService.findAllByPage(page, rows, sortBy, desc, key);
        return new PageResponse<Brand>(result.getTotalElements(), result.getTotalPages(), result.getContent());
    }

    @PostMapping()
    public Brand addBrand(@RequestBody Brand brand,
                                          @RequestParam("cids") List<Long> categories) {
        return brandService.add(brand, categories);
    }

    @PutMapping()
    public Brand updateBrand(@RequestBody Brand brand,
                                             @RequestParam("cids") List<Long> categories) {
        return brandService.update(brand, categories);
    }

    @DeleteMapping("/categoryBrand/{brandId}")
    public void deleteBrand(@PathVariable("brandId") long brandId) {
        brandService.deleteCategoryBrand(brandId);
    }

    @DeleteMapping("{brandId}")
    public void deleteBrand(@PathVariable("brandId") String bid) {
        String separator = "-";
        if (bid.contains(separator)) {
            String[] ids = bid.split(separator);
            for (String id : ids) {
                this.brandService.deleteBrand(Long.parseLong(id));
            }
        } else {
            this.brandService.deleteBrand(Long.parseLong(bid));
        }
    }

    /**
     * 查询指定分类的品牌集合
     *
     * @param categoryId 分类ID
     * @return List<Brand>
     */
    @GetMapping("/cid/{categoryId}")
    public List<Brand> findAllByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return brandService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/{brandId}")
    public Brand findById(@PathVariable("brandId") long brandId) {
        return brandService.findById(brandId);
    }

    @GetMapping("/list")
    public List<Brand> findAllByIdIn(@RequestParam("ids") List<Long> ids) {
        return brandService.findAllByIdIn(ids);
    }
}
