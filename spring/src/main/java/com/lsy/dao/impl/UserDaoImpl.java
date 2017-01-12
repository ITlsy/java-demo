package com.lsy.dao.impl;

import com.lsy.dao.UserDao;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Named;

@Repository
//@Service
//@Component
//@Named

//@Scope("prototype")
//@Lazy
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
