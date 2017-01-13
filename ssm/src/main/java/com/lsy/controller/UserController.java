package com.lsy.controller;

import com.lsy.exception.NotFoundException;
import com.lsy.pojo.Role;
import com.lsy.pojo.User;
import com.lsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<User> userList=userService.findAll();
        model.addAttribute("userList",userList);
        return "user/list";

    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Role> roleList=userService.findAllRole();
        model.addAttribute("roleList",roleList);
    return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user,Integer[] roleIds, RedirectAttributes redirectAttributes){
        userService.save(user,roleIds);
        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delUser(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        userService.delete(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return  "redirect:/user" ;
    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id,Model model){
        User user=userService.findUserById(id);
        if(user==null){
            throw new NotFoundException();
        }else {
            model.addAttribute("user",user);
            return"user/edit";
        }

    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.POST)
    public String editUser( User user,RedirectAttributes redirectAttributes){
        userService.editUser(user);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/user";
    }
}
