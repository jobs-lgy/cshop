package com.javachen.cshop.admin.controller;

import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.service.UserService;
import com.javachen.cshop.common.domain.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Api(tags = "管理员接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public RestResponse<User> save(@RequestBody @Valid User user) {
        return RestResponse.success(userService.save(user));
    }

    @PutMapping("/user")
    public RestResponse<User> update(@RequestBody @Valid User user) {
        return RestResponse.success(userService.save(user));
    }

    @DeleteMapping("/user/{id}")
    public void delete(@Valid @PathVariable Long id) {
        userService.delete(id);
    }


    @ApiOperation(value = "查询用户列表", notes = "分页查询")
    @GetMapping("/user")
    public RestResponse<List<User>> findAll() {
        return RestResponse.success(userService.findAll());
    }

    //FIXME 1、校验没有起作用
    @ApiOperation(value = "查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, paramType = "query", dataType = "Integer")
    })
    @GetMapping("/user/{id}")
    @Validated
    public RestResponse<User> findById(@Positive @PathVariable Long id) {
        return RestResponse.success(userService.findById(id));
    }

    @ApiOperation(value = "查询用户", notes = "根据用户名查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping("/user/username/{username}")
    public RestResponse<User> findByUsername(@PathVariable String username) {
        return RestResponse.success(userService.findByUsername(username));
    }
}
