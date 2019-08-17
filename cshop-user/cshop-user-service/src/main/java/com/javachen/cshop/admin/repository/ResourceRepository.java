package com.javachen.cshop.admin.repository;

import com.javachen.cshop.admin.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author june
 * @createTime 2019-08-02 15:28
 * @see
 * @since
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM UserResourceRel a, Resource r WHERE a.id.userId = ?1 AND a.id.resourceId = r.id")
    List<Resource> findAllByUserId(Long userId);

    @Query("SELECT r FROM RoleResourceRel a, Resource r WHERE a.id.roleId in ?1 AND a.id.resourceId = r.id")
    List<Resource> findAllByRoleIds(List<Long> roleIds);
}
