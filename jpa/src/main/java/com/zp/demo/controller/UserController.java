package com.zp.demo.controller;

import com.zp.demo.dao.UserDao;
import com.zp.demo.entity.Role;
import com.zp.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserDao userDao;

    @GetMapping("get")
    public Object get(){
        return userDao.findAll();
    }
    @GetMapping("save")
    public void save(){
        User user = new User();
        user.setName("111");
        Role role = new Role();
        role.setName("role");
        List list = new ArrayList();
        list.add(role);
        user.setRoles(list);
        userDao.save(user);
    }
}
