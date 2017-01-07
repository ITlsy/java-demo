package com.lsy.web.admin;

import com.lsy.dto.JsonResult;
import com.lsy.entitiy.Admin;
import com.lsy.service.AdminService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断当前是否有用户
        req.getSession().removeAttribute("curr_admin");
        forward("admin/login.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName=req.getParameter("adminName");
        String password=req.getParameter("password");
        String ip=req.getRemoteAddr();

        AdminService adminService=new AdminService();
        JsonResult jsonResult=new JsonResult();
        try {
            Admin admin=adminService.login(adminName,password,ip);
            req.getSession().setAttribute("curr_admin",admin);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch(Exception e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());

        }
        renderJson(jsonResult,resp);

    }
}
