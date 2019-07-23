package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.vo.UserVo;
import com.javachen.cshop.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User save(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @PutMapping
    public User update(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping
    public PageResponse<UserVo> findAll(
            @ApiParam("第几页")
            @Valid @Positive @RequestParam(required = false, defaultValue = "0") int page,
            @ApiParam("每页记录数")
            @Valid @Positive @RequestParam(required = false, defaultValue = "10") int size) {

        Page<User> result = userService.findAll(PageRequest.of(page, size));
        List<UserVo> userVoList=result.getContent().stream().map(user -> user.toVo()).collect(Collectors.toList());
        return new PageResponse<UserVo>(result.getTotalElements(), result.getTotalPages(),userVoList );
    }

    //FIXME 1、校验没有起作用   2、/user/getotp 的get请求映射到这里出现类型转换异常
    @GetMapping("/{id}")
    @Validated
    public UserVo findById(@ApiParam("用户id") @Positive @PathVariable Long id) {
        return userService.findById(id).toVo();
    }
}
