package com.javachen.cshop.admin.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PasswordReset implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String token;

    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 25, message = "新密码长度需要在6和25之间")
    private String newPassword;

    @NotNull(message = "确认密码不能为空")
    @Length(min = 6, max = 25, message = "确认密码长度需要在6和25之间")
    private String newPasswordConfirm;
}
