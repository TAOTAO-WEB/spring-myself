package com.blend.ibt.bean;

import com.blend.ibt.springframework.beans.factory.DisposableBean;
import com.blend.ibt.springframework.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

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
        System.out.println("test user:"+userDao.queryUserName(this.uid));
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：userService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行 userService.afterPropertiesSet");
    }
}
