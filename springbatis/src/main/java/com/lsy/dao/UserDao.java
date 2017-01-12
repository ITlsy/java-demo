package com.lsy.dao;

import com.lsy.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
public interface UserDao {
    void save(User user);
    void update(User user);
    User findById(Integer id);
    List<User> findAll();
    void delete(Integer id);
    User findByUsername(String username);
    Long count();

}
