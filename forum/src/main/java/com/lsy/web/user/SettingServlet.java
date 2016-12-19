package com.lsy.web.user;

import com.google.common.collect.Maps;
import com.lsy.dto.JsonResult;
import com.lsy.entitiy.User;
import com.lsy.exception.ServiceException;
import com.lsy.service.UserService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/setting")
public class SettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/setting.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action=req.getParameter("action");
    if("profile".equals(action)){
        updateProfile(req,resp);
    }else if("password".equals(action)){
        updatePassword(req,resp);

    }

    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldpassword=req.getParameter("oldpassword");
        String newpassword=req.getParameter("newpassword");

        User user=getCurrentUser(req);
        UserService userService=new UserService();
        try {
            userService.updatePassword(user, oldpassword, newpassword);
            JsonResult result = new JsonResult();
            result.setState(JsonResult.SUCCESS);

            renderJson(result, resp);
        }catch (ServiceException e){
            JsonResult result=new JsonResult(e.getMessage());
            renderJson(result,resp);


        }

    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email=req.getParameter("email");
        User user=getCurrentUser(req);

        UserService userService=new UserService();
        userService.updateEmail(user,email);

        Map<String,Object> result= Maps.newHashMap();
        result.put("state","success");
        renderJson(result,resp);

    }
}
