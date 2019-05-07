package com.zp.feign.controller;

import com.zp.feign.entity.User;
import com.zp.feign.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("login")
    public Object login(User user){
        Map res = new HashMap();
        if("zp".equals(user.name) && "1".equals(user.password)){
        }
        return new ResultTemplate(res);
    }
}
