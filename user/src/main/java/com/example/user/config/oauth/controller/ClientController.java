package com.example.user.config.oauth.controller;

import com.example.user.config.oauth.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/register")
//    public BaseResponse clientRegistered(@RequestBody @Valid BaseClientDetails client){
//		client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
//        boolean i = clientService.save(client);
//        return HttpResponse.baseResponse(200);
//    }

    @GetMapping("/api/author/test")
    public String apiTest(){
        return "hello apiTest";
    }

    @GetMapping("/api/demo/test")
    public String demoTest(){
        return "hello demoTest";
    }
}