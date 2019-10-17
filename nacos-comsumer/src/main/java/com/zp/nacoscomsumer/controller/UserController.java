package com.zp.nacoscomsumer.controller;

import com.zp.common.vo.Message;
import com.zp.nacoscomsumer.ForeignClient.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/all")
    public Message getAll(){
        return userClient.getAll();
    }
}
