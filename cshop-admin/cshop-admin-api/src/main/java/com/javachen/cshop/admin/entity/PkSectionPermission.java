package com.javachen.cshop.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
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
public class PkSectionPermission implements Serializable {
    private Long sectionId;
    private Long permissionId;
}
