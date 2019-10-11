package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

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

    @Async("taskExecutor")
    public Future<String> asyncTest() {
        try {
            log.info("运行前");
            Thread.sleep(Double.valueOf(Math.random()+ 5000).intValue());
            log.info("运行后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult("");
    }
}
