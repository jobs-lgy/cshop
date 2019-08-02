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
@IdClass(PkSectionPermission.class)
@Entity
@Data
public class SectionPermissionRel implements Serializable {
    @Id
    private Long sectionId;

    @Id
    private Long permissionId;
}
