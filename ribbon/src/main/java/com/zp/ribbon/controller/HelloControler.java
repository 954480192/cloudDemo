package com.zp.ribbon.controller;

import com.zp.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;
    @Value("${spring.application.name}")
    String appName;
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name+appName );
    }
}