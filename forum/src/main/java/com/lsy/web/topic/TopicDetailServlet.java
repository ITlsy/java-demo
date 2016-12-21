package com.lsy.web.topic;

import com.lsy.entitiy.Topic;
import com.lsy.exception.ServiceException;
import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid=req.getParameter("topicid");
        TopicService topicService=new TopicService();
        try {
            Topic topic=topicService.findTopicById(topicid);
            req.setAttribute("topic",topic);
            forward("/topic/topicDetail.jsp",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);

        }
    }
}
