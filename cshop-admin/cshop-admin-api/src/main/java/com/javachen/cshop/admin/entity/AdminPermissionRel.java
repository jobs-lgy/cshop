package com.javachen.cshop.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-08-02 18:57
 * @see
 * @since
 */
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkAdminPermission.class)
@Entity
@Data
public class AdminPermissionRel implements Serializable {
    @Id
    private Long adminId;

    @Id
    private Long permissionId;
}
