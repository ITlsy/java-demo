package com.lsy.service;

import com.lsy.entity.User;
import com.lsy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
       return userMapper.findAll();
    }
}
