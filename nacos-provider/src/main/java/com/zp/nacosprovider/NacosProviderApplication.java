package com.zp.nacosprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication

@RefreshScope
public class NacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }

    @GetMapping("/helloNacos")
    public String helloNacos(){
        return "你好，nacos！";
    }

    @Value("${nacos.config}")
    private String config;

//    @Value("${share}")
//    private String share;

    @RequestMapping("/getValue")
    public String getValue() {
        return config;
    }

//    @RequestMapping("/share")
//    public String share() {
//        return share;
//    }
}
