package com.example.user.config.security;

import com.example.user.config.oauth.MyAuthenticationFailureHandler;
import com.example.user.config.oauth.MyAuthenticationSuccessHandler;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * ResourceServerConfigurerAdapter 用于保护oauth相关的endpoints，同时主要作用于用户的登录(form login,Basic auth)
 * SecurityConfig 用于保护oauth要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

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
                    .antMatchers("/swagger-ui.html/**", "/webjars/**",
                        "/swagger-resources/**", "/v2/api-docs/**",
                        "/swagger-resources/configuration/ui/**",
                        "/swagger-resources/configuration/security/**",
                        "/images/**", "/css/**").permitAll()
                    .antMatchers("/loginPage", "/authentication/require","/","/user/all",
                            "/druid/*","oauth/**","/mobileCodeLogin","/login","/home").permitAll() // 这三个目录不做安全控制
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //指定登录页的路径
                    .loginPage("/loginPage")
                    .successHandler(authenticationSuccessHandler)
                    //指定自定义form表单请求的路径
                    .loginProcessingUrl("/login")// 默认就是login,可改为其他
                    .failureUrl("/login?error")
                    .failureHandler(authenticationFailureHandler)
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

    @Bean
    public MobileCodeAuthenticationFilter mobileCodeAuthenticationFilter() {
        MobileCodeAuthenticationFilter filter = new MobileCodeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
