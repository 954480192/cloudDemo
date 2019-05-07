package com.zp.feign.controller;

import com.zp.feign.config.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;
    @GetMapping("/hi")
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @GetMapping("/")
    public String index(){
        return "hello";
    }
}
