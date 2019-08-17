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

    /**
     * 查询指定分类的规格数据
     *
     * @param categoryId 分类ID
     * @return Specification
     */
    @GetMapping("/spec")
    public RestResponse<Spec> findByCategoryId(@RequestParam("categoryId") Long categoryId) {
        // 查询规格
        return RestResponse.success(specService.findByCategoryId(categoryId));
    }

    /**
     * 新增规格
     *
     * @param spec 规格
     * @return Specification
     */
    @PostMapping("/spec")
    public RestResponse<Spec> add(@RequestBody Spec spec) {
        return RestResponse.success(specService.add(spec));
    }

    /**
     * 更新规格
     *
     * @param spec 规格
     * @return Specification
     */
    @PutMapping("/spec")
    public RestResponse<Spec> update(@RequestBody Spec spec) {
        return RestResponse.success(specService.update(spec));
    }

    @DeleteMapping("/spec/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.specService.delete(id);
    }

//    @GetMapping("/params")
//    public ResponseEntity<List<SpecParam>> querySpecParam(
//            @RequestParam(value="gid", required = false) Long gid,
//            @RequestParam(value="cid", required = false) Long cid,
//            @RequestParam(value="searching", required = false) Boolean searching,
//            @RequestParam(value="generic", required = false) Boolean generic
//    ){
//        List<SpecParam> list =
//                this.specificationService.querySpecParams(gid,cid,searching,generic);
//        if(list == null || list.size() == 0){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(list);
//    }
}
