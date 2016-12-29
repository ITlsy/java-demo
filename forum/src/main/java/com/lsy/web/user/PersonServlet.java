package com.lsy.web.user;

import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.service.TopicService;
import com.lsy.util.Page;
import com.lsy.util.StringUtils;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/person")
public class PersonServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid=req.getParameter("nodeid");
        String p=req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        TopicService topicService=new TopicService();
        List<Node> nodeList=topicService.findAllNode();
        Page<Topic> page=topicService.findAllTopics(nodeid,pageNo);
        req.setAttribute("nodeList",nodeList);
        req.setAttribute("page",page);
        forward("user/person.jsp",req,resp);
    }
}
