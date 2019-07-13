package com.javachen.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-06-25 23:45
 * @see
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 25,message = "密码长度需要在6和25之间")
    private String password;
}