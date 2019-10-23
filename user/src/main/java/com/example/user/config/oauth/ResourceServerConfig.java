package com.example.user.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by liusj on 2019/9/17
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage("/loginPage")       // 登录跳转url
//                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")   // 处理表单登录url
                .successHandler(authenticationSuccessHandler)
//                .defaultSuccessUrl("/")
                .failureHandler(authenticationFailureHandler)
//                .failureUrl("/loginPage")
                .and()
                .authorizeRequests()            // 授权配置
                .antMatchers("/loginPage", "/css/**", "/authentication/require").permitAll()  // 无需认证
                .anyRequest()                   // 所有请求
                .authenticated()                // 都需要认证
                .and().csrf().disable();

    }

}