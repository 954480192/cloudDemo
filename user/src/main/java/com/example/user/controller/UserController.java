package com.example.user.controller;

import com.example.user.service.UserService;
import com.example.user.util.Message;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.subject.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

//    @RequiresPermissions("add")// 权限
    @RequestMapping("all")
    public Message getAll(){
        log.info("进入user服务，开始查询数据");
        Message msg = new Message();
        msg.setData(userService.getAll());
        return msg;
    }

    @GetMapping("evict")
    public String evict(){
        userService.evict();
        return "success";
    }

//    @PostMapping("login")
//    public String login(String name,String password){
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(new UsernamePasswordToken(name,password));
//        if(subject.isAuthenticated()){
//            return "success";
//        }
//        return "false";
//    }

    //async接口测试
    @RequestMapping("async")
    public Object ayncTest(){
        log.info("开始");
        String s = null;
        try {
            userService.asyncTest();
//            s = userService.asyncTest().get();
            log.info("结束");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
