package com.blend.ibt.bean;

import java.util.Random;

public class UserService3 implements IUserService{

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "this is query";
    }

    @Override
    public String register(String username) {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return username + "success";
    }
}
