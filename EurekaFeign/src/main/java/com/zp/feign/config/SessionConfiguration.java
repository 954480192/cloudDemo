package com.zp.feign.config;

import com.zp.feign.intercepter.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
    }

    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/")
//				.allowedOrigins("http://localhost:8082")
				.allowedMethods("*") //"PUT", "DELETE"
				.allowedHeaders("Content-Type", "x-requested-with", "Authorization")
//				.exposedHeaders("header1", "header2")
				.allowCredentials(true).maxAge(3600);
	}
}