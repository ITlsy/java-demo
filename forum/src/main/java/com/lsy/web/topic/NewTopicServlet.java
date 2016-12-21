package com.lsy.web.topic;

import com.lsy.dto.JsonResult;
import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.entitiy.User;
import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/newTopic")
public class NewTopicServlet extends BaseServlet {
    TopicService topicService=new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取nodelist到jsp页面
        List<Node> nodeList=topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("topic/newTopic.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        String nodeid=req.getParameter("nodeid");
        User user= (User) req.getSession().getAttribute("curr_user");
       // User user=getCurrentUser(req);
        Topic topic=topicService.addNewTopic(title,content,Integer.valueOf(nodeid),user.getId());
        JsonResult jsonResult=new JsonResult(topic);
        renderJson(jsonResult,resp);

    }
}
