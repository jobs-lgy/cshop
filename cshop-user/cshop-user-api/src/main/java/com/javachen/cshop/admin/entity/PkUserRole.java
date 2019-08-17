package com.javachen.cshop.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-08-02 18:57
 * @see
 * @since
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PkUserRole implements Serializable {
    private Long userId;
    private Long roleId;
}
