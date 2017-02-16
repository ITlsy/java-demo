package com.lsy.controller;

import com.lsy.exception.NotFoundException;
import com.lsy.pojo.Account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.IOUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        Account account=new Account();
        account.setUsername("lsy");
        account.setAge(18);

        Account account2=new Account();
        account2.setUsername("lhc");
        account2.setAge(1);

        List<Account> accountList= Arrays.asList(account,account2);
        model.addAttribute("accountList",accountList);
        return "user/list";

    }

   // @RequestMapping(value = "/new",method = RequestMethod.GET)
    @GetMapping("/new")
    public String newUser(){
        return "/user/add";
    }

     @RequestMapping(value = "/new",method = RequestMethod.POST)
     public String saveUser(Account account, RedirectAttributes redirectAttributes){
            System.out.println(account);
            //redirectAttributes是由过滤器原理，并setAttributes
            redirectAttributes.addFlashAttribute("message","添加成功");
            return "redirect:/user";


     }

     // @GetMapping(value = "/check/{username}",produces = "text/plain;charset=UTF-8")
    @RequestMapping(value = "/validate/{username}",method = RequestMethod.GET,
       produces = "text/plain;charset=UTF-8")
    @ResponseBody
     public String validateUsername(@PathVariable String username){
         if(username.equals("lsy")){
             return "no";
         }else {
             return "yes";
         }

     }

     @RequestMapping(value = "/api/{id:\\d+}",method = RequestMethod.GET)
     @ResponseBody
     public Account findById(@PathVariable Integer id){
         Account account=new Account();
         account.setUsername("李");
         account.setAge(23);
         return account;

     }

     @RequestMapping("/http")
     public String method(HttpServletRequest request ,HttpServletResponse response, HttpSession session){
    return "user/list";
     }

     @RequestMapping(value = "/upload",method = RequestMethod.GET)
     public String fileUpload(){
        return "user/upload";
     }

     @RequestMapping(value = "/uploader",method = RequestMethod.POST)
     public String saveFile(String name, MultipartFile file) throws IOException {
         System.out.println("name"+name);
         if(!file.isEmpty()){
             //上传表单控件的name属性值
             System.out.println("getName"+file.getName());
             //上传文件的原始名称
             System.out.println("getOriginalFilename"+file.getOriginalFilename());
             //文件大小（字节）
             System.out.println("getSize"+file.getSize());
             //文件的MIME类型
             System.out.println("getContentType"+file.getContentType());
             InputStream inputStream = file.getInputStream();
             FileOutputStream outputStream=new FileOutputStream(new File(""));



         }
            return "redirect:/user";
     }



     @RequestMapping("/u-{id://d+}")
    public String showUser(@PathVariable Integer id){
         if(id==100){
             throw new NotFoundException();
         }
         return "redirect:/user";

     }
}
