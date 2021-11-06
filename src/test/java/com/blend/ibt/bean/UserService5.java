package com.blend.ibt.bean;

import com.blend.ibt.springframework.beans.factory.annotation.Autowired;
import com.blend.ibt.springframework.beans.factory.annotation.Value;
import com.blend.ibt.springframework.stereotype.Component;

import java.util.Random;

@Component("userService5")
public class UserService5 implements IUserService{

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao2 userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    @Override
    public String register(String username) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + username + " success！";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao2 getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao2 userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return "UserService5{" +
                "token='" + token + '\'' +
                '}';
    }
}
