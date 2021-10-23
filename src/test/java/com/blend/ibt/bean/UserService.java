package com.blend.ibt.bean;

public class UserService {
    private String username;
    private String uid;
    private UserDao userDao;

    public UserService(){

    }

    public UserService(String username){
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "username='" + username + '\'' +
                ", uid='" + uid + '\'' +
                ", userDao=" + userDao +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void queryUser(){
        System.out.println("test user:"+this.username+"，查询用户信息:"+userDao.queryUserName(this.uid));
    }
}
