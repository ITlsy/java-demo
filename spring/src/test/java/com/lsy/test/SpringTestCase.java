package com.lsy.test;

import com.lsy.dao.UserDao;
import com.lsy.dao.impl.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class SpringTestCase {
    @Test
    public void load(){
        ApplicationContext applicationContext=
                new ClassPathXmlApplicationContext("applicationContext.xml");
       /* UserDaoImpl userDao=
                applicationContext.getBean("userDaoImpl",UserDaoImpl.class);*/
      /*UserDaoImpl userDao2=applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        System.out.println(userDao==userDao2);
*/
       /* userDao.save();
        userDao.update();*/
    }
}
