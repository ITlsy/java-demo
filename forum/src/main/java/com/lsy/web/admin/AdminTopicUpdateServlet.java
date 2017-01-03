package com.lsy.web.admin;

import com.lsy.dto.JsonResult;
import com.lsy.exception.ServiceException;
import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/1 0001.
 */
@WebServlet("/admin/topicUpdate")
public class AdminTopicUpdateServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid=req.getParameter("id");
        String nodeid=req.getParameter("nodeid");

        TopicService topicService=new TopicService();
        JsonResult jsonResult=new JsonResult();
        try{
            topicService.updateTopicNode(topicid,nodeid);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult=new JsonResult("参数异常");

        }
        renderJson(jsonResult,resp);
    }
}
