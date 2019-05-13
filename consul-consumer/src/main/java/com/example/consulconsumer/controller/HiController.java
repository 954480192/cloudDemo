package com.example.consulconsumer.controller;

import com.example.consulconsumer.service.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    EurekaClientFeign hiService;

    @GetMapping("/hi")
    public String sayHi(){
        return hiService.sayHiFromClientEureka();
    }
}