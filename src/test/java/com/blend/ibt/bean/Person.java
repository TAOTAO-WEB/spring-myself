package com.blend.ibt.bean;

import java.util.Date;

public class Person {
    private String wifiName;
    private Date marriageDate;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "wifiName='" + wifiName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }
}
