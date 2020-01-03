//package com.zp.cloud_common.components;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.GsonHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    converters.add(0, new MappingJackson2HttpMessageConverter());
//  }
//
//
////  @Override
////  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////    // TODO Auto-generated method stub
////    // WebMvcConfigurer.super.configureMessageConverters(converters);
////    converters.add(gsonJsonHttpMessageConverter());// Gson和FastJson取其一，我这取Gson
////    // converters.add(fastJsonHttpMessageConverter());
////  }
////
////  @Bean
////  public GsonHttpMessageConverter gsonJsonHttpMessageConverter() {
////    // 使用Gson
////    return new GsonHttpMessageConverter();// Gson转换json的Converter
////  }
//
////  @Bean
////  public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
////    // 使用fastJson
////    return new FastJsonHttpMessageConverter();// FastJson转换json的Converter
////  }
//}