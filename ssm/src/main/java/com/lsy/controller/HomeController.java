package com.lsy.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * Created by Administrator on 2017/1/12 0012.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String login(String username, String password, RedirectAttributes redirectAttributes){
        Subject subject= SecurityUtils.getSubject();
        try{
        subject.login(new UsernamePasswordToken(username,password));
        return "redirect:/home";
        }catch (AuthenticationException e){
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("message","账号或密码错误");
        return "redirect:/";
        }



    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes){
        //安全退出
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";

    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "demo";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }
}
