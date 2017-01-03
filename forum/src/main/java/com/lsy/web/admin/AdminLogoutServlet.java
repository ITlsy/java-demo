package com.lsy.web.admin;

import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/1 0001.
 */
@WebServlet("/admin/logout")
public class AdminLogoutServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        //强制清楚session
        session.invalidate();
        req.setAttribute("message","已安全退出");
        forward("admin/login.jsp",req,resp);
    }
}
