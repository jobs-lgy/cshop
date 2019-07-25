package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.vo.UserVo;
import com.javachen.cshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags="用户接口")
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


    @ApiOperation(value = "查询用户列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, paramType = "query", dataType = "Integer")
    })
    @GetMapping("/user")
    public RestResponse<PageResponse<UserVo>> findAll(
            @Valid @Positive @RequestParam(required = false, defaultValue = "0") int page,
            @Valid @Positive @RequestParam(required = false, defaultValue = "10") int size) {
        Page<User> result = userService.findAll(PageRequest.of(page, size));
        List<UserVo> userVoList=result.getContent().stream().map(user -> user.toVo()).collect(Collectors.toList());
        return RestResponse.success(new PageResponse<UserVo>(result.getTotalElements(), result.getTotalPages(),userVoList ));
    }

    //FIXME 1、校验没有起作用
    @ApiOperation(value = "查询用户", notes="根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, paramType = "query", dataType = "Integer")
    })
    @GetMapping("/user/{id}")
    @Validated
    public RestResponse<UserVo> findById(@Positive @PathVariable Long id) {
        return RestResponse.success(userService.findById(id).toVo());
    }
}
