package com.javachen.cshop.controller;

import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.common.response.PageResponse;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public CommonResponse save(@RequestBody @Valid User user) {
        userService.save(user);
        return CommonResponse.success();
    }

    @PutMapping
    public CommonResponse update(@RequestBody @Valid User user) {
        userService.save(user);
        return CommonResponse.success();
    }

    @DeleteMapping("/{id}")
    public CommonResponse delete(@Valid @PathVariable Long id) {
        userService.delete(id);
        return CommonResponse.success();
    }

    @GetMapping
    public CommonResponse<PageResponse<User>> findAll(
            @ApiParam("第几页")
            @Valid @Positive @RequestParam(required = false, defaultValue = "0") int page,
            @ApiParam("每页记录数")
            @Valid @Positive @RequestParam(required = false, defaultValue = "10") int size) {

        Page<User> result= userService.findAll(PageRequest.of(page, size));
        return CommonResponse.success(new PageResponse<User>(result.getTotalElements(),result.getTotalPages(),result.getContent()));
    }

    //FIXME 1、校验没有起作用   2、/user/getotp 的get请求映射到这里出现类型转换异常
    @GetMapping("/{id}")
    @Validated
    public CommonResponse<User> findById(@ApiParam("用户id") @Positive @PathVariable Long id) {
        return CommonResponse.success(userService.findById(id));
    }
}
