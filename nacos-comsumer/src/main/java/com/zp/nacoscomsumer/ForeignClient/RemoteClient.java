package com.zp.nacoscomsumer.ForeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nacos-provide",fallback = RemoteClient.RemoteHystrix.class)
public interface RemoteClient {

    @GetMapping("/helloNacos")
    String helloNacos();

    @Component
    static class RemoteHystrix implements RemoteClient {
        @Override
        public String helloNacos() {
            return "请求超时了";
        }
    }
}

