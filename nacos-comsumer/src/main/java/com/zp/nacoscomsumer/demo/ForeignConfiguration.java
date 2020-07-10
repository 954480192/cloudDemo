package com.zp.nacoscomsumer.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zp.nacoscomsumer.ForeignClient.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@RestController
public class ForeignConfiguration {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    // -----------------  ribbon ----------------------
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/consumer")
    @SentinelResource(value="consumer")
    public String test1() {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
//        String url = String.format("http://%s:%s/%s",serviceInstance.getHost(),serviceInstance.getPort(),"helloNacos");
//        System.out.println("request url:"+url);
//        return restTemplate.getForObject(url,String.class);
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
