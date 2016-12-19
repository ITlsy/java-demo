package com.lsy.service;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import com.google.common.cache.LoadingCache;
import com.lsy.dao.UserDao;
import com.lsy.entity.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class UserServiceWithGuava {



   /* private static LoadingCache<String,User> cache= CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(50, TimeUnit.SECONDS)
            .build(new CacheLoader<String, User>() {
                @Override
                public User load(String key) throws Exception {
                  UserDao userDao=new UserDao();
                    return userDao.findById(Integer.valueOf(key));
                }
            });

    public User findById(Integer id){
        User user=cache.getUnchecked(id.toString());//cache.get("user:"+id);
        return user;

    }
*/


   private UserDao userDao=new UserDao();
   private static Cache<String,User> cache=CacheBuilder.newBuilder()
           .maximumSize(100)
           .expireAfterAccess(10,TimeUnit.MINUTES)
           .build();

   public User findById(Integer id){
       User user=null;
       try {
           user=cache.get("user:" + id, new Callable<User>() {
               @Override
               public User call()  {
                   return userDao.findById(id);
               }
           });
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
       return user;

   }
}
