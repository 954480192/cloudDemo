package com.example.user.config.oauth;

import com.example.user.config.oauth.service.BootClientDetailsService;
import com.example.user.config.oauth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
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
        //reuseRefreshTokens设置为false时，每次通过refresh_token获得access_token时，也会刷新refresh_token；也就是说，会返回全新的access_token与refresh_token。
        //默认值是true，只返回新的access_token，refresh_token不变。
        endpoints.reuseRefreshTokens(true);
        // 自定义授权页面接口路径，最后一个参数为替换之后授权页面的url
        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");
//        默认只支持post  这里添加get支持
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);// add get method
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
                    // 允许的授权范围
                    .scopes("all", "a", "b", "c")
                    //回调uri，在authorization_code与implicit授权方式时，用以接收服务器的返回信息
                    .redirectUris("http://localhost:8866")
                    // 该client允许的授权类型，不同的类型，则获得token的方式不一样。
                    .authorizedGrantTypes("password", "refresh_token","authorization_code","implicit")
                .and()
                    .withClient("test2")
                    .secret(new BCryptPasswordEncoder().encode("test2222"))
                    .accessTokenValiditySeconds(7200);
    }

}