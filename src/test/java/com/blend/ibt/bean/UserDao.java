package com.blend.ibt.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("init-method");
        hashMap.put("10001","first");
        hashMap.put("10002","sec");
        hashMap.put("10003","three");
    }

    public void destroyDataMethod(){
        System.out.println("destroy-method");
        hashMap.clear();
    }


    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}
