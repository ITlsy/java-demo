package com.lsy.controller;

import com.lsy.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
     return "user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "user/home";
    }


}
