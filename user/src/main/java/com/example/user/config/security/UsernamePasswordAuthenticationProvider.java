package com.example.user.config.security;

import com.example.user.dao.UserDao;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.thymeleaf.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 身份认证
 */
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userDao.findByName(username);
        // 认证用户名
//        if (!"user".equals(username) && !"admin".equals(username)) {
//            throw new BadCredentialsException("用户不存在");
//        }
//        // 认证密码，暂时不加密
//        if ("user".equals(username) && !"123".equals(password) || "admin".equals(username) && !"admin".equals(password)) {
//            throw new BadCredentialsException("密码不正确");
//        }

        if(null == user ){
            throw new BadCredentialsException("用户不存在！");
        }
        if(!password.equals(user.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username,
                authentication.getCredentials(), listUserGrantedAuthorities(user));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Set<GrantedAuthority> listUserGrantedAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });

//        if ("admin".equals(username)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
        return authorities;
    }
}