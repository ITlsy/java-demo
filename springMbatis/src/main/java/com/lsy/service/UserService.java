package com.lsy.service;

import com.lsy.pojo.User;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public interface UserService {

    void save(User user) throws Exception;
    User findById(Integer id);
}
