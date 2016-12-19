package com.lsy.web;

import com.lsy.util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
@WebServlet("/vocab")
public class VocabularyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query=req.getParameter("query");
        String url="http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype" +
                "=xml&version=1.1&q="+query;
        String result= HttpUtil.sendHttpGetRequestWithString(url);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.print(result);
        out.flush();
        out.close();

    }
}
