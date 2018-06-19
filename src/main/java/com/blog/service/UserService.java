package com.blog.service;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;


    @Transactional
    public void signUpUser(String username, String password){
        userDao.signUpUser(username,password);
    }
    @Transactional
    public boolean checkUserPassword(String username, String password){
        return userDao.checkPassword(username,password);
    }
    @Transactional
    public int findMatchUser(String username){
        return userDao.findMatchUser(username);
    }

    @Transactional
    public User loginUser(String username, String password){
        return userDao.loginUser(username,password);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
