package com.javachen.cshop.auth.oauth2;

import com.google.common.collect.Lists;
import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.auth.feign.AccountClient;
import com.javachen.cshop.common.domain.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义认证
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    public AccountClient accountClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        RestResponse<User> restResponse= accountClient.findByUsername(username);

        if(restResponse.getCode()==0 && restResponse.getData()==null){
            log.error("feign client occur error:{}",restResponse.getMessage());
            throw new UsernameNotFoundException("用户不存在");
        }

        User user =restResponse.getData();

        // 默认所有用户拥有 USER 权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
