package com.javachen.cshop.admin.repository;

import com.javachen.cshop.admin.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author june
 * @createTime 2019-08-02 15:28
 * @see
 * @since
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
