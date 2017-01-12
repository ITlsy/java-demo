package com.lsy.mapper;

import com.lsy.pojo.User;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public interface UserMapper {
        void save(User user);
        User findById(Integer id);
}
