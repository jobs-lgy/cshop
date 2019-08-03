package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Resource;
import com.javachen.cshop.admin.service.AdminSecurityHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Component
public class AdminSecurityHelperImpl implements AdminSecurityHelper {

    @Override
    public void addAllPermissionsToAuthorities(List<SimpleGrantedAuthority> grantedAuthorities, Collection<Resource> resources) {
        for (Resource resource : resources) {
            grantedAuthorities.add(new SimpleGrantedAuthority(resource.getAbbr()));
        }
    }
}
