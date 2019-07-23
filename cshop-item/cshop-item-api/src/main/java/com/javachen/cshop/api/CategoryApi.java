package com.javachen.cshop.api;

import com.javachen.cshop.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {
    @GetMapping("names")
    public List<String> findAllNameByIdIn(@RequestParam("ids") List<Long> ids);

    @GetMapping("list")
    public List<Category> findAllByIdIn(@RequestParam("ids") List<Long> ids);
}
