package com.lsy.web;

import com.lsy.pojo.User;
import com.lsy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        User user=new User();
        user.setId(2);
        user.setName("lsy");
        userService.save(user);
        resp.sendRedirect("hello.jsp");

    }
}
