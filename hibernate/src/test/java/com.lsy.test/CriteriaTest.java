package com.lsy.test;

import com.lsy.pojo.User;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class CriteriaTest {

    @Test
    public void findAll(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Criteria criteria=session.createCriteria(User.class);
        List<User> userList=criteria.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void where(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Criteria criteria=session.createCriteria(User.class);
       /* criteria.add(Restrictions.eq("userName","c1"));
        criteria.add(Restrictions.eq("password","333"));*/

      /* criteria.add(Restrictions.or(Restrictions.eq("userName","a1"),
       Restrictions.eq("userName","c1")));*/
        criteria.addOrder(Order.desc("id"));
        Disjunction disjunction=Restrictions.disjunction();
        disjunction.add(Restrictions.eq("userName","a1"));
        disjunction.add(Restrictions.eq("userName","c2"));

        criteria.add(disjunction);
//      分页
     /* criteria.setFirstResult(0);
      criteria.setMaxResults(2);*/
      //criteria.add(Restrictions.like("userName","i", MatchMode.ANYWHERE));
        List<User> userList=criteria.list();
        for (User user:userList){
            System.out.println( user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void count(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Criteria criteria=session.createCriteria(User.class);
        ProjectionList projectionList=Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);
        Object[] result= (Object[]) criteria.uniqueResult();
        System.out.println(result[0]+" "+result[1]);

        session.getTransaction().commit();
    }
}
