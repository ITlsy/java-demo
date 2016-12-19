package com.lsy.service;

import com.lsy.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserServiceWithGuavaTest {
    private UserService userService=new UserService();
    @Test
    public void findById() throws Exception {
        User user=userService.findById(4);

        user=userService.findById(4);
        System.out.println(user);

    }

}