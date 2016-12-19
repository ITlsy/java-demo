package com.lsy.web;

import com.google.gson.Gson;
import com.lsy.entity.User;

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
@WebServlet("/jsonp")
public class JsonpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        User user = new User(1001,"Jack","北京");
        String json=new Gson().toJson(user);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(method+"("+json+")");// callback({id:xx,username:xxx})
        out.flush();
        out.close();

    }
}
