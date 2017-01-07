package com.lsy.web.admin;

import com.lsy.dto.JsonResult;
import com.lsy.entitiy.Node;
import com.lsy.exception.ServiceException;
import com.lsy.service.NodeService;

import com.lsy.service.TopicService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
@WebServlet("/admin/newNode")
public class NewnodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            forward("admin/newNode.jsp",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodename=req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult=new JsonResult();
        try {
            nodeService.addNode(nodename);
            jsonResult.setState(jsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(jsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());

        }
        renderJson(jsonResult,resp);
    }
}
