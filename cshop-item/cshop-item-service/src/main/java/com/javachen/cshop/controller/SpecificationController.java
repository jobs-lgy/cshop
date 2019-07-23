package com.javachen.cshop.controller;

import com.javachen.cshop.entity.Specification;
import com.javachen.cshop.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 查询指定分类的规格数据
     *
     * @param categoryId 分类ID
     * @return Specification
     */
    @GetMapping("/spec")
    public Specification querySpecificationByCategoryId(@RequestParam("categoryId") Long categoryId) {
        // 查询规格
        return specificationService.findByCategoryId(categoryId);
    }

    /**
     * 新增规格
     *
     * @param specification 规格
     * @return Specification
     */
    @PostMapping("/spec")
    public Specification add(@RequestBody Specification specification) {
        return specificationService.add(specification);
    }

    /**
     * 更新规格
     *
     * @param specification 规格
     * @return Specification
     */
    @PutMapping("/spec")
    public Specification updateSpecification(@RequestBody Specification specification) {
        return specificationService.update(specification);
    }

    @DeleteMapping("/spec/{id}")
    public void deleteSpecification(@PathVariable("id") Long id) {
        this.specificationService.delete(id);
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
