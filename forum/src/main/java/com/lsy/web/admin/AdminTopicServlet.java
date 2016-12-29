package com.lsy.web.admin;

import com.lsy.entitiy.Topic;
import com.lsy.exception.ServiceException;
import com.lsy.service.AdminService;
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
@WebServlet("/admin/topic")
public class AdminTopicServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p=req.getParameter("p");
        Integer pageNo= StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        TopicService topicService=new TopicService();
        Page<Topic> page=topicService.findAllTopics("",pageNo);
        req.setAttribute("page",page);
        forward("admin/topic.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        AdminService adminService=new AdminService();
        try {
        adminService.delTopicById(id);
        renderText("success",resp);

        }catch (ServiceException e){
            renderText(e.getMessage(),resp);

        }
    }
}
