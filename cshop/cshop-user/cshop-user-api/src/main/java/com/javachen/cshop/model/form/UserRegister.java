package com.javachen.cshop.model.form;

import com.javachen.cshop.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author june
 * @createTime 2019-06-25 23:45
 * @see
 * @since
 */
@Data
@NoArgsConstructor
public class UserRegister {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;

    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 25,message = "密码长度需要在6和25之间")
    private String password;

    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 25,message = "密码长度需要在6和25之间")
    private String passwordConfirm;

    @NotNull(message = "手机验证码不能为空")
    private String code;

    public User getUser(){
        User user=new User();
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setUsername(this.username);
        return user;
    }
}
