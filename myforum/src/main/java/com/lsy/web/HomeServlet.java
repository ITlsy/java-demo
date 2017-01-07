package com.lsy.web;

import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.service.TopicService;
import com.lsy.util.Page;
import com.lsy.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid=req.getParameter("nodeid");
        String p=req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        if(StringUtils.isNotEmpty(nodeid)&&!StringUtils.isNumeric(nodeid)){
            forward("index.jsp",req,resp);
            return;
        }
        TopicService topicService=new TopicService();
        List<Node> nodeList=topicService.findAllNode();
        Page<Topic> page=topicService.findAllTopics(nodeid,pageNo);
        req.setAttribute("nodeList",nodeList);
        req.setAttribute("page",page);
        forward("index.jsp",req,resp);
    }
}
