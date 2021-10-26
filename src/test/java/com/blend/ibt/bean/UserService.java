package com.blend.ibt.bean;

public class UserService {

    private String username;
    private String uid;
    private UserDao userDao;
    private String company;
    private String location;

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
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
