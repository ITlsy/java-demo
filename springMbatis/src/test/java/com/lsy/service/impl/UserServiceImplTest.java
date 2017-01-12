package com.lsy.service.impl;

import com.lsy.pojo.User;
import com.lsy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void save() throws Exception{
        User user=new User("niu","123");
        userService.save(user);
    }
   @Test
    public void findById() {
        User user=userService.findById(1);
        System.out.println(user);
       assertNotNull(user);
    }

}
