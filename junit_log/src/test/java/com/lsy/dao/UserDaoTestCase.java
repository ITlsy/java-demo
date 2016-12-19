package com.lsy.dao;

import com.lsy.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*; //静态导入

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class UserDaoTestCase {
    private UserDao userDao;


    @Before
    public void before(){
        userDao=new UserDao();
    }

    @Test
    public void testSave(){
        User user=new User();
        user.setUsername("junit");
        user.setAge(18);
        user.setAddress("ufo");
        user.setTel("3666");

        userDao.save(user);
    }

    @Test
    public void testFindById(){

        User user=userDao.findById(4);
        user=userDao.findById(4);
        user=userDao.findById(2);
        assertNotNull(user);

    }
    @Test
    public void testFindAll(){
        List<User> userList=userDao.findAll();
        assertEquals(4,userList.size());
    }
    @Test
    public void testDel(){
        userDao.del(3);
    }

    @Test
    public void testSystem(){
        String str=System.getProperty("java.io.tmpdir");
        System.out.println(str);
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
    }



}
