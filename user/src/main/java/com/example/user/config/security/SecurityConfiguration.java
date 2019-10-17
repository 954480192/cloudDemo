package com.example.user.config.security;

import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 创建内存用户
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder.encode("123")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN");
        auth.authenticationProvider(usernamePasswordAuthenticationProvider())
                .authenticationProvider(mobileCodeAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/user/all","/druid/*","/mobileCodeLogin","/login","/home").permitAll() // 这三个目录不做安全控制
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //指定登录页的路径
                    .loginPage("/loginPage")
                    //指定自定义form表单请求的路径
                    .loginProcessingUrl("/login")// 默认就是login,可改为其他
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
                    .permitAll()// 自定义登陆页面路径
                    .and()
                .logout().logoutSuccessUrl("/");// 退出后到首页
//                     .and()
//                .sessionManagement().invalidSessionUrl("session/invalid");    //session过期后跳转的URL
//                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
        http.addFilterBefore(mobileCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http .csrf().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public MobileCodeAuthenticationFilter mobileCodeAuthenticationFilter() {
        MobileCodeAuthenticationFilter filter = new MobileCodeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
    // spring security 必须有一个passwordEncoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
        return new UsernamePasswordAuthenticationProvider();
    }

    @Bean
    public MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider() {
        return new MobileCodeAuthenticationProvider();
    }
}
