package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService{
    @Autowired
    UserDao userDao;

//    @Cacheable(cacheNames = "user.all")
    public List<User> getAll() {
        return userDao.findAll();
    }

//    @CacheEvict(cacheNames = "user.all")
    public void evict() {
    }
}
