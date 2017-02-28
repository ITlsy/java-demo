package com.lsy.service;

import com.lsy.dao.UserDao;
import com.lsy.pojo.User;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public class UserService {
    private UserDao userDao=new UserDao();

    public void save(User user){
        userDao.save(user);
    }
}
