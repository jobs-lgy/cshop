package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.Brand;
import com.javachen.cshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public PageResponse<Brand> findByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                                @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                                @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                @RequestParam(value = "key", required = false) String key) {
        Page<Brand> result = brandService.findAllByPage(page, size, sortBy, desc, key);
        return new PageResponse<Brand>(result.getTotalElements(), result.getTotalPages(), result.getContent());
    }

    @PostMapping("/brand")
    public Brand addBrand(@RequestBody Brand brand,@RequestParam("cids") List<Long> categories) {
        return brandService.add(brand, categories);
    }

    @PutMapping("/brand")
    public Brand updateBrand(@RequestBody Brand brand,@RequestParam("cids") List<Long> categories) {
        return brandService.update(brand, categories);
    }

    @GetMapping("/brand/{id}")
    public Brand findById(@PathVariable("id") long id) {
        return brandService.findById(id);
    }

    @DeleteMapping("/brand/categoryBrand/{brandId}")
    public void deleteCategoryBrand(@PathVariable("brandId") long brandId) {
        brandService.deleteCategoryBrand(brandId);
    }

    /**
     * 查询指定分类的品牌集合
     *
     * @param categoryId 分类ID
     * @return List<Brand>
     */
    @GetMapping("/brand/category/{categoryId}")
    public List<Brand> findAllByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return brandService.findAllByCategoryId(categoryId);
    }


    @DeleteMapping("/brand/ids/{ids}")
    public void deleteBrand(@PathVariable("ids") List<Long> ids) {
        for (Long id : ids) {
            this.brandService.delete(id);
        }
    }

    @GetMapping("/brand/ids/{ids}")
    public List<Brand> findAllByIdIn(@PathVariable("ids") List<Long> ids) {
        return brandService.findAllByIdIn(ids);
    }
}
