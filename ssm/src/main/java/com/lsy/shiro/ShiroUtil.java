package com.lsy.shiro;

import com.lsy.pojo.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class ShiroUtil {

    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserName(){
        return getCurrentUser().getUsername();
    }

    public static Integer getCurrentUserId(){
        return getCurrentUser().getId();
    }

    public static boolean isSales(){
        return SecurityUtils.getSubject().hasRole("role_sales");
    }
}
