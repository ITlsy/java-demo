package com.lsy.web.topic;

import com.lsy.dto.JsonResult;
import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.exception.ServiceException;
import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/topicEdit")
public class TopicEditServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid=req.getParameter("topicid");
        TopicService topicService=new TopicService();
        Topic topic=topicService.findTopicById(topicid);

        List<Node> nodeList=topicService.findAllNode();
        req.setAttribute("topic",topic);
        req.setAttribute("nodeList",nodeList);
        forward("topic/topicEdit.jsp",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        String nodeid=req.getParameter("nodeid");
        String topicid=req.getParameter("topicid");

        JsonResult jsonResult=null;
        if(StringUtils.isNumeric(topicid)){
          TopicService topicService=new TopicService();
          try {
              topicService.updateTopicById(title, content, nodeid, topicid);

              jsonResult = new JsonResult();
              jsonResult.setState(jsonResult.SUCCESS);
              jsonResult.setData(topicid);
          }catch (ServiceException e){
              jsonResult=new JsonResult(e.getMessage());

          }
        }else{
            jsonResult= new JsonResult("参数异常");
        }
        renderJson(jsonResult,resp);
    }
}
