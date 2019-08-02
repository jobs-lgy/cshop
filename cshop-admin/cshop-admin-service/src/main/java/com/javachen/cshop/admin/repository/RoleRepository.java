package com.javachen.cshop.admin.repository;

import com.javachen.cshop.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author june
 * @createTime 2019-08-02 15:30
 * @see
 * @since
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM AdminRoleRel a, Role r WHERE a.id.adminId = ?1 AND a.id.roleId = r.id")
    List<Role> findAllByAdminId(Long adminId);
}
