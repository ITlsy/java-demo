package com.lsy.service;

import com.lsy.dao.UserDao;
import com.lsy.entity.User;
import com.lsy.util.EhCacheUtil;

import java.util.List;


/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserService {
    private static final String USER_CACHE_NAME="user";
    private UserDao userDao=new UserDao();
    private EhCacheUtil ehCacheUtil=new EhCacheUtil();

    public User findById(Integer id){
    User user= (User) ehCacheUtil.get(USER_CACHE_NAME,"user:"+id);
    if(user==null){
      user=userDao.findById(id);
      ehCacheUtil.set(USER_CACHE_NAME,"user:"+id,user);
    }
    return user;

    }
    public List<User> findAll(){
        List<User> userList= (List<User>) ehCacheUtil.get(USER_CACHE_NAME,"userList");
        if(userList==null){
          userList= userDao.findAll();
          ehCacheUtil.set(USER_CACHE_NAME,"userList",userList);

        }
        return userList;
    }
    public void save(User user){
        userDao.save(user);
        ehCacheUtil.remove(USER_CACHE_NAME,"userList");

    }
    public void del(Integer id){
        userDao.del(id);
        ehCacheUtil.removeAll(USER_CACHE_NAME);
        /*ehCacheUtil.remove(USER_CACHE_NAME,"userList");
        ehCacheUtil.remove(USER_CACHE_NAME,"user:"+id);*/

    }
}
