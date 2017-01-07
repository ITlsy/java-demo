package com.lsy.web.admin;

import com.lsy.entitiy.TopicReplyCount;
import com.lsy.service.TopicService;
import com.lsy.util.Page;
import com.lsy.util.StringUtils;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
@WebServlet("/admin/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String p=req.getParameter("p");
       Integer pageNo= StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        TopicService topicService=new TopicService();
        Page<TopicReplyCount> page=topicService.getTopicnumAndReplynumByDayList(pageNo);
        req.setAttribute("page",page);
        forward("admin/home.jsp",req,resp);
    }
}
