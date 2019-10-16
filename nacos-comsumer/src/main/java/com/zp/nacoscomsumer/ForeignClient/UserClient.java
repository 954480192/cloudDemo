package com.zp.nacoscomsumer.ForeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "nacos-user")
public interface UserClient {

    @RequestMapping("/user/all")
    public Message getAll();

}
