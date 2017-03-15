package com.lsy.test;

import com.lsy.pojo.User;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class HQLTest {

    @Test
    public void where(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        /*String hql="from User where userName=:name";//引用占位符
        Query query=session.createQuery(hql);
        query.setParameter("name","a1");*/
       /* String hql="from User where userName=?";//引用占位符
        Query query=session.createQuery(hql);
        query.setString(0,"a1");*/
        String hql="from User where userName=:name and password=:pwd";//引用占位符
        Query query=session.createQuery(hql);
        query.setParameter("name","hibernate ORM");
        query.setParameter("pwd","4.x");

        query.setFirstResult(2);
        query.setMaxResults(2);

        List<User> userList=query.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void queryProperty(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        String hql="select id ,userName,password from User";
        Query query=session.createQuery(hql);
        List<Object[]> nameList=query.list();
        for (Object[] object:nameList){
            System.out.println(object[0]+"->"+object[1]+"->"+object[2]);
        }
        session.getTransaction().commit();
    }

    @Test
    public void count(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        String hql="select count(*) from User";
        Query query=session.createQuery(hql);
        Long count= (Long) query.uniqueResult();
        System.out.println(count);
        session.getTransaction().commit();
    }
}
