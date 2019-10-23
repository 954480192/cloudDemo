package com.zp.nacoscomsumer.controller;

import com.zp.common.vo.Message;
import com.zp.nacoscomsumer.ForeignClient.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserClient userClient;

    @ApiOperation(value = "获取所有用户", notes = "all用户")
    @GetMapping("/all")
    public Message getAll(){
        log.info("consume 开始调用 user");
        return userClient.getAll();
    }
}
