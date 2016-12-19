package com.lsy.service;

import com.lsy.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserServiceTest {
    private UserService userService=new UserService();
    @Test
    public void findById() throws Exception {
        User user=userService.findById(4);
        user=userService.findById(4);
        System.out.println(user);

    }
    @Test
    public void findAll(){
        List<User> userList=userService.findAll();
        userList=userService.findAll();
        System.out.println(userList);
    }
    @Test
    public void save(){
        List<User> userList=userService.findAll();
        int beforeSize=userList.size();
        System.out.println("size:"+userList.size());
        User user=new User();
        user.setUsername("cache");
        user.setAge(24);
        user.setAddress("jepan");
        user.setTel("4758");
        userService.save(user);

        userList=userService.findAll();
        int afterSize=userList.size();
        System.out.println("size:"+userList.size());

        Assert.assertEquals(beforeSize+1,afterSize);

    }


}