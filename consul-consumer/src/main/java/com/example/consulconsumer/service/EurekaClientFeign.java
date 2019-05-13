package com.example.consulconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-provider")
public interface EurekaClientFeign {

 
    @GetMapping(value = "/hi")
    String sayHiFromClientEureka();
}