package com.lsy.web.topic;

import com.lsy.dto.JsonResult;
import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.entitiy.User;
import com.lsy.exception.ServiceException;
import com.lsy.service.TopicService;
import com.lsy.util.Config;
import com.lsy.web.BaseServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

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
        Auth auth=Auth.create(Config.get("qiniu.ak"),Config.get("qiniu.sk"));
        StringMap map=new StringMap();
        map.put("returnBody","{ \"success\": true,\"file_path\": \""+Config.get("qiniu.domain")+"${key}\"}");
        //  String str="{\"success\":true,\"file_path\":\"Config.get(\"qiniu.domain\")+\"${key}\"\"}";
        String token=auth.uploadToken(Config.get("qiniu.bucket"),null,3600,map);
        //获取nodelist到jsp页面
        List<Node> nodeList=topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        req.setAttribute("token",token);
        forward("topic/newTopic.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        String nodeid=req.getParameter("nodeid");
        User user= (User) req.getSession().getAttribute("curr_user");
        // User user=getCurrentUser(req);
        JsonResult jsonResult=null;
        Topic topic=null;
        try {
             topic = topicService.addNewTopic(title, content, Integer.valueOf(nodeid), user.getId());
             jsonResult=new JsonResult(topic);
        }catch (ServiceException e){
            jsonResult=new JsonResult(e.getMessage());

        }

        renderJson(jsonResult,resp);

    }
}
