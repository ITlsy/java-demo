package com.lsy.web.admin;

import com.lsy.entitiy.Node;
import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
@WebServlet("/admin/node")
public class NodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicService topicService=new TopicService();
        List<Node> nodeList=topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/node.jsp",req,resp);
    }
}
