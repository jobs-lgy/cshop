package com.javachen.cshop.admin.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PasswordChange implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 25, message = "密码长度需要在6和25之间")
    private String currentPassword;

    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 25, message = "新密码长度需要在6和25之间")
    private String newPassword;

    @NotNull(message = "确认密码不能为空")
    @Length(min = 6, max = 25, message = "确认密码长度需要在6和25之间")
    private String newPasswordConfirm;

}
