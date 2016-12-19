package com.lsy.web;

import com.lsy.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

@WebServlet("/user.xml")
public class UserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user=new User(01,"jack","china");
        User user2=new User(02,"lili","USA");
        User user3=new User(03,"james","UK");
        List<User> userList= Arrays.asList(user,user2,user3);
        //1. 设置响应的字符编码
        resp.setCharacterEncoding("UTF-8");
        //2. 设置响应头MIME type
        resp.setContentType("text/xml;charset=UTF-8");

        PrintWriter out=resp.getWriter();
        out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        根节点
        out.print("<users>");
        for(User u:userList){
            out.print("<user id=\""+u.getId()+"\">");
            out.print("<name>"+u.getUserName()+"</name>");
            out.print("<address>"+u.getAddress()+"</address>");
            out.print("</user>");
        }
        out.print("</users>");
        out.flush();
        out.close();



    }
}
