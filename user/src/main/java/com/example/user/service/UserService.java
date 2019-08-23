package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAll(){
        return userDao.findAll();
    }

}
