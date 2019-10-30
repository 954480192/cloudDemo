package com.example.user.config.oauth;

import com.example.user.config.oauth.service.BootClientDetailsService;
import com.example.user.config.oauth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    BootClientDetailsService clientDetailsService;
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    private TokenStore jwtTokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userDetailService);
        // 最后一个参数为替换之后授权页面的url
        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库中获取client参数
//        clients.withClientDetails(clientDetailsService);
        // 写死内存中（测试用）
        clients.inMemory()
                    .withClient("test1")
                    .secret(new BCryptPasswordEncoder().encode("test1111"))
                    .accessTokenValiditySeconds(3600)
                    .refreshTokenValiditySeconds(864000)  // 10天
                    .scopes("all", "a", "b", "c")
                    .authorizedGrantTypes("password", "refresh_token")
                .and()
                    .withClient("test2")
                    .secret(new BCryptPasswordEncoder().encode("test2222"))
                    .accessTokenValiditySeconds(7200);
    }

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("test1:test1111".getBytes()));
    }
}