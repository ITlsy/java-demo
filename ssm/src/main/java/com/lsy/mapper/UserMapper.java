package com.lsy.mapper;

import com.lsy.pojo.User;

import java.util.List;


public interface UserMapper {
    List<User> findAll();

    void save(User user);

    User findById(Integer id);

    void delete(Integer id);

    void update(User user);


}
