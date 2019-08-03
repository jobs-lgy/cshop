package com.javachen.cshop.admin.security.jwt;

import com.javachen.cshop.admin.entity.Resource;
import com.javachen.cshop.admin.entity.Role;
import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.service.AdminSecurityHelper;
import com.javachen.cshop.admin.service.ResourceService;
import com.javachen.cshop.admin.service.RoleService;
import com.javachen.cshop.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    protected AdminSecurityHelper adminSecurityHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return buildDetails(username, user);
    }

    protected UserDetails buildDetails(final String username, final User user) {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        addRoles(user, authorities);
        addPermissions(user, authorities);

        return createDetails(username, user, authorities);
    }

    protected void addRoles(final User user,
                            final List<SimpleGrantedAuthority> authorities) {
        List<Role> roles = roleService.findAllByUserId(user.getId());
        if(roles==null||roles.size()<=0){
            return;
        }
        for (final Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAbbr()));
        }

        List<Resource> resources = resourceService.findAllByRoleIds(roles.stream().map(role -> role.getId()).collect(Collectors.toList()));
        adminSecurityHelper
                .addAllPermissionsToAuthorities(authorities, resources);
    }

    protected void addPermissions(final User user,
                                  final List<SimpleGrantedAuthority> authorities) {
        List<Resource> resources = resourceService.findAllByUserId(user.getId());
        adminSecurityHelper
                .addAllPermissionsToAuthorities(authorities, resources);
    }

    protected UserDetails createDetails(final String username, final User user,
                                        final List<SimpleGrantedAuthority> authorities) {
        return new JwtUserDetails(user.getId(), username,
                user.getPassword(), true, true, true, true, authorities);
    }
}