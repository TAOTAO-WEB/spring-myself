package com.blend.ibt.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001","first");
        hashMap.put("10002","sec");
        hashMap.put("10003","three");
    }

    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}
