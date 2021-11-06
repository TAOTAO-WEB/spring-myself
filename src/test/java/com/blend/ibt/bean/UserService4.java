package com.blend.ibt.bean;

import com.blend.ibt.springframework.stereotype.Component;

import java.util.Random;

//@Component("userService")
public class UserService4 implements IUserService{

    private String token;

    @Override
    public String queryUserInfo() {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "query user info";
    }

    @Override
    public String register(String username) {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户："+username+"success!";
    }

    @Override
    public String toString() {
        return "UserService4{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
