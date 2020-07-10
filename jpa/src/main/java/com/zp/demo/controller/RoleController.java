package com.zp.demo.controller;

import com.zp.demo.dao.RoleDao;
import com.zp.demo.entity.Role;
import com.zp.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleDao roleDao;

    @GetMapping("get")
    public Object get(){
        return roleDao.findAll();
    }
    @GetMapping("save")
    public void save(){
        Role role = new Role();
        role.setName("role2");
        User user = new User();
        user.setName("22");
        List users = new ArrayList();
        users.add(user);
        role.setUsers(users);
        roleDao.save(role);
    }
}
