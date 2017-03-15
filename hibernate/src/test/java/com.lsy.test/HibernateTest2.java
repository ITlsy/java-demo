package com.lsy.test;

import com.lsy.pojo.User;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class HibernateTest2 {

    /*
    * object->持久态
    */
    @Test
    public void getAndLoad(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        /*
        * 区别；get->主键值没有，则user为null，输出id则会使用查询
        * load->主键值没有，则出现找不到异常，输出id不会使用查询，延迟查询
        */
      //  User user= (User) session.get(User.class,44);
        User user= (User) session.load(User.class,444);

       // System.out.println(user.getId());
        System.out.println(user);
      //  System.out.println(user.getUserName());
        session.getTransaction().commit();
    }


    /*
    * object->自由态->持久态
    */
    @Test
    public void saveOrPersist(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user=new User();
        user.setUserName("a1");
        user.setPassword("111");
       session.persist(user);//无返回值
      /*Integer id= (Integer) session.save(user);//返回id值
        System.out.println(id);*/
       // System.out.println(user.getId());
        session.getTransaction().commit();
    }

    /*
    * 游离态->持久态
    */
    @Test
    public void saveAndUpdate(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user=new User();//自由态
        user.setUserName("b1");
        user.setPassword("222");
        session.save(user);//持久态
        session.getTransaction().commit();//游离态
        Session session2=SessionFactoryUtil.getSession();
        session2.getTransaction().begin();
        user.setUserName("b2");
        session2.update(user);//持久态
        session2.getTransaction().commit();
    }

    /*
    *object->自由态->持久态
     */
    @Test
    public void saveOrUpdate(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user=new User();
        user.setUserName("c1");
        user.setPassword("333");
        session.saveOrUpdate(user);
        session.getTransaction().commit();

        Session session2=SessionFactoryUtil.getSession();
        session2.getTransaction().begin();
        user.setUserName("c2");
        session2.saveOrUpdate(user);
        session2.getTransaction().commit();
    }

    /*
    * 操作完，对象状态不变
    */
    @Test
    public void merge(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user=new User();
        user.setUserName("d1");
        user.setPassword("333");
        session.save(user);
        session.getTransaction().commit();

        Session session2=SessionFactoryUtil.getSession();
        session2.getTransaction().begin();
        user.setUserName("d2");
        session2.merge(user);
        session2.getTransaction().commit();
    }

    /*
    * 持久态->游离态
    */
    @Test
    public void clearAndFlush(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
       User user= (User) session.get(User.class,44);
        user.setUserName("e2");
      session.flush();//立即同步到数据库中
        //session.clear();
        session.getTransaction().commit();

    }
}
