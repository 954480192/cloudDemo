package com.example.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "login";
    }
}
