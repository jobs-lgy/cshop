package com.javachen.cshop.admin.security.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class JwtUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private Long id;

    public JwtUserDetails(final Long id,
                          final String username,
                          final String password,
                          final Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, true, true, true, true, authorities);
    }

    public JwtUserDetails(final Long id,
                          final String username,
                          final String password,
                          final boolean enabled,
                          final boolean accountNonExpired,
                          final boolean credentialsNonExpired,
                          final boolean accountNonLocked,
                          final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        setId(id);
    }

    public JwtUserDetails withId(final Long id) {
        setId(id);
        return this;
    }
}