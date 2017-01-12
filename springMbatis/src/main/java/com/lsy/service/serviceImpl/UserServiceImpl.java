package com.lsy.service.serviceImpl;

import com.lsy.mapper.UserMapper;
import com.lsy.pojo.User;
import com.lsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(User user) throws Exception {
        userMapper.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
