package com.javachen.cshop.admin.security;

import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.admin.entity.Role;
import com.javachen.cshop.admin.service.AdminSecurityHelper;
import com.javachen.cshop.admin.service.AdminService;
import com.javachen.cshop.admin.service.PermissionService;
import com.javachen.cshop.admin.service.RoleService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Resource
    protected AdminService adminService;

    @Resource
    protected RoleService roleService;

    @Resource
    protected AdminSecurityHelper adminSecurityHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Admin admin = adminService.findByUsername(username);
        if (admin == null) {
            admin = adminService.findByEmail(username);
        }
        if (admin == null || !admin.isActive()) {
            throw new UsernameNotFoundException("The user was not found");
        }

        return buildDetails(username, admin);
    }

    protected UserDetails buildDetails(final String username, final Admin admin) {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        addRoles(admin, authorities);
        addPermissions(admin, authorities);

        return createDetails(username, admin, authorities);
    }

    protected void addRoles(final Admin admin,
                            final List<SimpleGrantedAuthority> authorities) {
        List<Role> roles = roleService.findAllByAdminId(admin.getId());
        for (final Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
//            adminSecurityHelper
//                    .addAllPermissionsToAuthorities(authorities, role.getAllPermissions());
        }
    }

    protected void addPermissions(final Admin admin,
                                  final List<SimpleGrantedAuthority> authorities) {
//        adminSecurityHelper
//                .addAllPermissionsToAuthorities(authorities, admin.getAllPermissions());

        for (final String perm : PermissionService.DEFAULT_PERMISSIONS) {
            authorities.add(new SimpleGrantedAuthority(perm));
        }
    }

    protected UserDetails createDetails(final String username, final Admin admin,
                                        final List<SimpleGrantedAuthority> authorities) {
        return new AdminDetails(admin.getId(), username,
                admin.getPassword(), true, true, true, true, authorities);
    }
}
