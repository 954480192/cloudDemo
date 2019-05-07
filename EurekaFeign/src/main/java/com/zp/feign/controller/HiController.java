package com.zp.feign.controller;

import com.zp.feign.config.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name+appName);
    }

    @GetMapping("/")
    public String index(){
        return "hello";
    }
}
