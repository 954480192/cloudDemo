package com.example.user.controller;

import com.example.user.service.UserService;
import com.example.user.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("all")
    public Message getAll(){
        Message msg = new Message();
        msg.setData(userService.getAll());
        return msg;
    }
}
