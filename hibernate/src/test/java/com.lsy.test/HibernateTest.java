package com.lsy.test;

import com.lsy.pojo.Task;
import com.lsy.pojo.User;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;


public class HibernateTest {

    @Test
    public void save(){
       //1.创建sessionFactory
        Configuration configuration=new Configuration().configure();
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry);
        //2.创建session
        Session session=sessionFactory.getCurrentSession();
        //3.事务
        Transaction transaction=session.getTransaction();
        transaction.begin();

        User user=new User();
        user.setUserName("hibernate");
        user.setPassword("4.x");
        session.save(user);

        System.out.println(user.getId());

       /* Task task=new Task();
        task.setTitle("hibernate");
        session.save(task);*/

        //4.结束
        transaction.commit();
//        session.close();
    }

    @Test
    public void findById() throws InterruptedException {
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();

        Task task= (Task) session.get(Task.class,"402881885acff76d015acff7720e0000", LockMode.OPTIMISTIC);
        task.setTitle("xxx");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Session session2=SessionFactoryUtil.getSession();
                session2.getTransaction().begin();
                Task task2= (Task) session2.get(Task.class,"402881885acff76d015acff7720e0000");
                task2.setTitle("zzzz");
                session2.getTransaction().commit();
            }
        });
        thread.start();
        Thread.sleep(3000);
       // Task task=session.get(Task.class,)
        /*User user= (User) session.get(User.class,43);
        System.out.println(user);*/
        session.getTransaction().commit();
    }

    @Test
    public void update(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user= (User) session.get(User.class,1);
        user.setUserName("hibernate ORM、");
        session.getTransaction().commit();
    }

    @Test
    public void delete(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        User user= (User) session.get(User.class,33);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void findAll(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        String hql="from User";
        Query query=session.createQuery(hql);
        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();

    }
    @Test
    public void findByUserName(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        String hql="from User where userName=?";
        Query query=session.createQuery(hql);
        System.out.println(query.setParameter(0,"lsy"));

        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }


}
