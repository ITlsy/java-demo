package com.lsy.test;

import com.lsy.pojo.User;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class myBatisCaseTest {
    @Test
    public void readXml(){
        //1.读取classpath中的配置文件
        try {
            Reader reader= Resources.getResourceAsReader("mybatis.xml");
            //2.构建sqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            //3.创建sqlSession对象
            SqlSession sqlSession=sqlSessionFactory.openSession();
            //4.代码执行
            User user=sqlSession.selectOne("com.lsy.mapper.UserMapper.findById",1);
            System.out.println(user);
            //5.释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void findById(){
        SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();
       User user=sqlSession.selectOne("com.lsy.mapper.UserMapper.findById",1);
        System.out.println(user);
        sqlSession.close();

    }
    @Test
    public void save(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        User user=new User();
        user.setUsername("lsy");
        user.setPassword("123456");
        sqlSession.insert("com.lsy.mapper.UserMapper.save",user);
        System.out.println(user.getId());
        //提交事务
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void update(){
        //自动提交
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession(true);
        User user=sqlSession.selectOne("com.lsy.mapper.UserMapper.findById",1);
        user.setUsername("rose");
        sqlSession.update("com.lsy.mapper.UserMapper.update",user);
        sqlSession.close();

    }
    @Test
    public void delete(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession(true);
        sqlSession.delete("com.lsy.mapper.UserMapper.delete",7);
        sqlSession.close();
    }
   @Test
    public void findAll(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
       List<User> userList=sqlSession.selectList("com.lsy.mapper.UserMapper.findAll");

        for(User user:userList){
            System.out.println(user);
        }

        sqlSession.close();
    }



}
