package com.blend.ibt.bean;

public class Husband {
    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife(){
        return wife;
    }

    public void setWife(Wife wife){
        this.wife = wife;
    }

}
