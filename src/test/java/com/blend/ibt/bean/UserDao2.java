package com.blend.ibt.bean;

import com.blend.ibt.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao2 {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "first");
        hashMap.put("10002", "sec");
        hashMap.put("10003", "third");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
