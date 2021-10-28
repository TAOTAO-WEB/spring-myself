package com.blend.ibt.bean;

import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.factory.*;
import com.blend.ibt.springframework.context.ApplicationContext;
import com.blend.ibt.springframework.context.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean,
        BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware,BeanNameAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

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

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader:"+classLoader);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is:"+name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
