package com.javachen.cshop.admin.service;

import com.javachen.cshop.admin.entity.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface AdminSecurityHelper {
    void addAllPermissionsToAuthorities(List<SimpleGrantedAuthority> grantedAuthorities, Collection<Resource> resources);
}
