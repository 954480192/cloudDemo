package com.zp.feign.service;

import com.zp.feign.config.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiService implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        String result = "[Feign] Sorry, " + name +"，我是熔断器的回调方法返回值，我的出现代表服务已出现故障";
        System.out.println(result);
        return result;
    }
}
