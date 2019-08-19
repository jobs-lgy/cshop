package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.item.entity.Spec;
import com.javachen.cshop.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("/spec")
    public RestResponse<Spec> findByCategoryId(@RequestParam("categoryId") Long categoryId) {
        return RestResponse.success(specService.findByCategoryId(categoryId));
    }

    @PostMapping("/spec")
    public RestResponse<Spec> add(@RequestBody Spec spec) {
        return RestResponse.success(specService.add(spec));
    }

    @PutMapping("/spec")
    public RestResponse<Spec> update(@RequestBody Spec spec) {
        return RestResponse.success(specService.update(spec));
    }

    @DeleteMapping("/spec/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.specService.delete(id);
    }
}
