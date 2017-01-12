package com.lsy;


import com.lsy.dao.UserDao;
import com.lsy.dao.impl.UserDaoImpl;
import com.lsy.service.UserService;
import com.lsy.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
@Configuration
@ComponentScan//自动扫描
@EnableAspectJAutoProxy //aop注解
public class Application {
    //别人的文件，无法加注解
  /*  @Bean
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
    @Bean
    public UserService getUserService(){
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);
        return userService;
    }*/

}
