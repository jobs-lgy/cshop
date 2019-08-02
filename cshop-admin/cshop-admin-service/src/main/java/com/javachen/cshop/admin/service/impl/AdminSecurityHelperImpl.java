package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Permission;
import com.javachen.cshop.admin.service.AdminSecurityHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Component
public class AdminSecurityHelperImpl implements AdminSecurityHelper {

    @Override
    public void addAllPermissionsToAuthorities(List<SimpleGrantedAuthority> grantedAuthorities, Collection<Permission> permissions) {
        for (Permission permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
//            for (Permission childPermission : permission.getAllChildPermissions()) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(childPermission.getName()));
//            }
        }
    }
}
