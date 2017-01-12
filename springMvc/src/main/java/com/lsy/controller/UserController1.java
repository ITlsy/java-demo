package com.lsy.controller;

import com.lsy.pojo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController1 {

    // @RequestMapping("/add")
    /*@GetMapping("/add")//springmvc版本>=4.3*/
 @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
    return "user/add";
    }

   // @PostMapping("/add")
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    //方法里面的参数是getParamter方法
    public String save(Account account){
        System.out.println(account.getUsername()+","+account.getAge());
        return "redirect:/home";

    }

  /*  @RequestMapping(method = RequestMethod.GET)
    public String showUser(int id){
        System.out.println("id"+id);
        return "redirect:/home";

    }*/

    /*@RequestMapping(value = "/{id:\\d{3,}}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id){
        System.out.println("PATH ID: "+id);
        return "redirect:/home";

    }*/

   /* @RequestMapping(value = "/{id:\\d+}/{types}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id, @PathVariable(name = "types") String type, Model model){
        System.out.println("ID: "+id +"Type: "+type);
        model.addAttribute("id",id);
        model.addAttribute("type",type);
        return "user/show";
    }*/

    /*@RequestMapping(value = "/{id:\\d+}/{types}",method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable Integer id,@PathVariable(name="types") String type){
        System.out.println("ID: "+id +"Type: "+type);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("user/show");
        mav.addObject("id",id);
        mav.addObject("type",type);

        return mav;


       // return new ModelAndView("user/show","id",id);
    }*/
}
