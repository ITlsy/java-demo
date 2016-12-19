package com.lsy.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
       /* //三个回应头不能使用缓存
        resp.setContentType("text/html;charset=UTF-8");
        resp.addHeader("pragma","no-cache");
        resp.addHeader("cache-control","no-cache");
        resp.addHeader("expires","0");*/

        //get方法中文编写，url中含有中文
        name=new String(name.getBytes("ISO8859-1"),"UTF-8");
        System.out.println("hello,ajax->"+name);

        PrintWriter out=resp.getWriter();
        out.print("hello");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //表单提交(POST)含有中文
        req.setCharacterEncoding("UTF-8");
        //给客户端设置响应编码
        resp.setCharacterEncoding("UTF-8");
        //设置响应内容(纯字符串)
        resp.setContentType("text/plain;charset=UTF-8");

        String name=req.getParameter("name");
        PrintWriter out=resp.getWriter();
        System.out.println(name);

        if("tom".equals(name)){
            System.out.println("账号被占用");
            out.print("不能用");
        }else{
            System.out.println("账号可以使用");
            out.print("能用");
        }
        System.out.println("hello,ajax,doPost->"+name);
        out.flush();
        out.close();
    }
}
