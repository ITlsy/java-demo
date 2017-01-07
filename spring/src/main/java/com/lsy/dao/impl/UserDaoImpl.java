package com.lsy.dao.impl;

import com.lsy.dao.UserDao;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class UserDaoImpl implements UserDao {
    public UserDaoImpl(){
        System.out.println("createUserDaoImpl");
    }
    public void save() {
        System.out.println("UserDaoImpl save");
    }

    public void update() {
        System.out.println("UserDaoImpl update");

    }
}
