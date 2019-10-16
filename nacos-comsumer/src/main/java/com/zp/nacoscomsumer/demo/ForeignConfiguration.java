package com.zp.nacoscomsumer.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zp.nacoscomsumer.ForeignClient.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ForeignConfiguration {

    @Autowired
    private RestTemplate restTemplate;

    // -----------------  ribbon ----------------------
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/consumer")
    @SentinelResource(value="consumer")
    public String test1() {
        return restTemplate.getForObject("http://nacos-provide/helloNacos",String.class);
    }


    // ---------------- feign ------------------
    @Autowired
    private RemoteClient remoteClient;

    @GetMapping("/feign")
    public String test() {
        return remoteClient.helloNacos();
    }
}
