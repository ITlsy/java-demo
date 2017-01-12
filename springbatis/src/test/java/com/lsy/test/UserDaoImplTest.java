package com.lsy.test;

import com.lsy.dao.UserDao;
import com.lsy.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void save() throws Exception {
        User user=new User("spring","4");
        userDao.save(user);
    }

    @Test
    public void update() throws Exception {
    User user=userDao.findById(10);
    user.setUsername("m");
        userDao.update(user);

    }

    @Test
    public void findById() throws Exception {
    User user=userDao.findById(21);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void findAll() throws Exception {
    List<User> userList=userDao.findAll();
    for (User user:userList){
        System.out.println(user);
    }
    }

    @Test
    public void delete() throws Exception {
    userDao.delete(17);
    }

    @Test
    public void findByUsername() throws Exception {
    User user=userDao.findByUsername("mei");
        System.out.println(user);
    assertNotNull(user);
    }

    @Test
    public void count() throws Exception{
        Long result=userDao.count();
        System.out.println(result);

    }

}