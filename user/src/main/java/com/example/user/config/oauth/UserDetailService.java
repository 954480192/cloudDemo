package com.example.user.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 授权请求连接
 * /oauth/authorize?response_type=code&client_id=test&redirect_uri=https://www.baidu.com&scope=all&state=hello
 */


@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = new MyUser();
        //任意账号 密码123456 admin权限
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));
        return user;
    }
}