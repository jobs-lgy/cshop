package com.javachen.model.vo;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author june
 * @createTime 2019-06-25 23:36
 * @see
 * @since
 */
@Data
public class UserModel {
    private Long id;
    private String name;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;

    private Boolean active=false;

    private Date createTime;// 创建时间

    @UpdateTimestamp
    private Date updateTime;// 最后修改时间
}
