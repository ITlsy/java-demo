package com.lsy.service;

import com.lsy.pojo.Role;
import com.lsy.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public interface UserService {
    List<User> findAll();

    void save(User user);

    User findUserById(Integer id);

    void delete(Integer id);

    void editUser(User user, Integer[] roleIds);

    List<Role> findAllRole();

    void save(User user, Integer[] roleIds);
}
