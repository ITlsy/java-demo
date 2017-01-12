package com.lsy.service.impl;

import com.lsy.dao.UserDao;
import com.lsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

   // 如果注解在属性上，则set方法不用要
   @Autowired
   // @Resource
   // @Inject
    private UserDao userDao;

    //set方法，
   //@Autowired
   /* public void setUserDao(UserDao userDao){
        this.userDao=userDao;

    }*/


     /*
    private String name;
    private Integer age;
    private List<String> list;
    private Set<Double> set;
    private Map<String,Object> map;
    private Properties properties;*/

/*
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Double> set) {
        this.set = set;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
*/


   /* public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;

    }*/

    @Override
    public void save() {
        userDao.save();
    }

    @Override
    public void update() {
        userDao.update();
    }

    @Override
    public int getNum() {
        System.out.println("getNum....");
       /* if(1==1){
          throw new RuntimeException("yichang");
        }*/

        return 100;
    }


}
