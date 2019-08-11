package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Resource;
import com.javachen.cshop.admin.service.UserSecurityHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Component
public class UserSecurityHelperImpl implements UserSecurityHelper {

    @Override
    public void addAllPermissionsToAuthorities(List<SimpleGrantedAuthority> grantedAuthorities, Collection<Resource> resources) {
        for (Resource resource : resources) {
            grantedAuthorities.add(new SimpleGrantedAuthority(resource.getAbbr()));
        }
    }
}
