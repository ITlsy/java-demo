package com.lsy.entity;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class User {
    private int id;
    private String  userName;
    private String address;

    public User() {
    }

    public User(int id, String userName, String address) {
        this.id = id;
        this.userName = userName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
