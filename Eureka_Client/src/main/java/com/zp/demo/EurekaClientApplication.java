package com.zp.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    //读取配置文件参数，测试用
    @Value("${server.port}")
    String port;
    @RequestMapping("")
    public Object home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
//        Map res = new HashMap();
//        res.put("a",name);
//        return  res;
    }

}

