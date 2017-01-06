package com.lsy.test;

import com.lsy.mapper.UserMapper;
import com.lsy.pojo.User;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class MybatisInterfaceTestCase {
        private UserMapper userMapper;
        private SqlSession sqlSession;
    @Before
    public void startup(){
        sqlSession=SqlSessionFactoryUtil.getSqlSession();
         userMapper=sqlSession.getMapper(UserMapper.class);
    }
    @After
    public void close(){
        sqlSession.close();
    }

    @Test
    public void findById(){
        SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();
        //自动产生一个UserMapper接口的实现类（代理对象），动态代理模式
        //UserMapper是接口，userMapper是接口的实现类，getMapper是接口的实现方法，UserMapper.class是接口的类的表现
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.findById(1);
        System.out.println(user);
        sqlSession.close();
    }
@Test
   public void save(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession(true);
        User user=new User();
        user.setUsername("lyz");
        user.setPassword("216");
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        userMapper.save(user);
       System.out.println(user.getId());

      // sqlSession.commit();
       sqlSession.close();
   }
    @Test
   public void findByUsernameAndPassword(){
    SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
    UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
    User user=userMapper.findByUsernameAndPassword("rose","123123");
    User user2=userMapper.findByUsernameAndPassword2("lsy","123456");
        Map<String,Object> param=new HashMap<>();
        param.put("username","mei");
        param.put("password","123");
    User user3=userMapper.findByUsernameAndPassword3(param);

    System.out.println(user);
    System.out.println(user2);
    System.out.println(user3);
        sqlSession.close();
   }

   @Test
    public void findByParam(){
       Map<String,Object> param=new HashMap<>();
       param.put("username","lsy");
        //param.put("password","123456");
       User user=userMapper.findByParam(param);
       System.out.println(user);
   }
@Test
   public void findByIds(){
       List<User> userList=userMapper.findByIds(Arrays.asList(1,4,9,10));
       for (User user:userList){
           System.out.println(user);
       }
   }
   @Test
   public void batchSave(){
       List<User> userList=new ArrayList<>();
       userList.add(new User("qq","123"));
       userList.add(new User("aa","123"));
       userList.add(new User("dd","123"));
       userMapper.bacthSave(userList);
       sqlSession.commit();
   }

   @Test
    public void findByCache(){
       //一级缓存：同一个SqlSession中（默认开启）
       User user=userMapper.findById(1);
       user=userMapper.findById(1);
       user=userMapper.findById(1);
       System.out.println(user);
   }
   @Test
    public void findByCache2(){
       //二级缓存（默认关闭）同一个SqlSessionFactory中
       SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
       UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
       User user=userMapper.findById(1);
       System.out.println(user);
       sqlSession.close();

       SqlSession sqlSession2=SqlSessionFactoryUtil.getSqlSession();
       UserMapper userMapper2=sqlSession2.getMapper(UserMapper.class);
               User user2=userMapper2.findById(1);
       System.out.println(user2);
       sqlSession2.close();
   }
}
