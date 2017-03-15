package com.lsy.test;

import com.lsy.pojo.User;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class NativeSqlQueryTest {
    @Test
    public void findAll(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        /*String sql="select * from t_user";
        SQLQuery query=session.createSQLQuery(sql);
        List<Object[]> userList=query.list();
        for (Object[] user:userList){
            System.out.println(user[0]+"->"+user[1]);
        }*/

        String sql="select * from t_user";
        SQLQuery query=session.createSQLQuery(sql).addEntity(User.class);
        List<User> userList=query.list();
        for (User user:userList){
            System.out.println(user);
        }


        session.getTransaction().commit();
    }
}
