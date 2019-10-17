package com.zp.nacoscomsumer.ForeignClient;

import com.zp.common.vo.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "nacos-user",fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @RequestMapping("/user/all")
    public Message getAll();

    @Component
    static class UserClientFallback implements UserClient{
        @Override
        public Message getAll() {
            return new Message("请稍后再试！",false,null);
        }
    }

}
