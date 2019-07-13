package com.javachen.cshop.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author june
 * @createTime 2019-07-13 16:29
 * @see
 * @since
 */
@Data
@NoArgsConstructor
public class UserVo implements Serializable {
    private Long id;

    private String username;

    private String phone;

    private String email;

    private Boolean active = false;

    private Date createTime;// 创建时间

    private Date updateTime;// 最后修改时间
}
