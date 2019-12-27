package com.example.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/")
    public String hello(HttpServletRequest request, Principal principal, Authentication authentication){
        // 获取当前用户
        System.out.println(request.getUserPrincipal().getName());
        System.out.println(principal.getName());
        System.out.println(authentication.getPrincipal().toString());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return "hello";
    }

    @RequestMapping("/loginPage")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/login");
        return "base-login";
    }

//    @GetMapping("/logout")
//    public String wuJinLogout(@RequestParam("token")String accessToken){
//        //注销token
//        if (consumerTokenServices.revokeToken(accessToken)) {
//            return "login";
//        }
//        return "/login?error";
//    }

}
