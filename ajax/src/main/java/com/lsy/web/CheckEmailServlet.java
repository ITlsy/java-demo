package com.lsy.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
@WebServlet("/checkemail")
public class CheckEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");

        System.out.println("EMail:" + email);
        PrintWriter out=resp.getWriter();
        if("lsy@qq.com".equals(email)){
            out.print("false");
        }else {
            out.print("true");
        }
        out.flush();
        out.close();
    }
}
