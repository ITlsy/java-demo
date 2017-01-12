package com.lsy.test;

import com.lsy.Application;
import com.lsy.dao.UserDao;
import com.lsy.dao.impl.UserDaoImpl;
import com.lsy.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class SpringTestCase {
    @Test
    public void load(){
        ApplicationContext applicationContext=
               // new ClassPathXmlApplicationContext("applicationContext.xml");
                    new AnnotationConfigApplicationContext(Application.class);

                /* UserDaoImpl userDao=
                applicationContext.getBean("userDaoImpl",UserDaoImpl.class);*/

      /*UserDaoImpl userDao2=applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        System.out.println(userDao==userDao2);
*/
       /* userDao.save();
        userDao.update();*/

       /* UserDao userDao= (UserDao) applicationContext.getBean("userDaoImpl");
        userDao.save();*/

       /* UserService userService= (UserService) applicationContext.getBean("userService");
                userService.save();
                userService.getNum();*/

       //bean注解管理时，默认类名首字母小写userServiceImpl
        UserService userService= (UserService) applicationContext.getBean("userServiceImpl");
        userService.save();
        userService.getNum();
    }
}
