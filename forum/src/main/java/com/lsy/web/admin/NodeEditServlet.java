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
 * Created by Administrator on 2016/12/28 0028.
 */
@WebServlet("/admin/nodeEdit")
public class NodeEditServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid=req.getParameter("nodeid");
        TopicService topicService=new TopicService();
        try {
            Node node=topicService.findNodeById(nodeid);
            req.setAttribute("node",node);
            forward("admin/nodeEdit.jsp",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid=req.getParameter("nodeid");
        String nodename=req.getParameter("nodename");
        System.out.println(nodename);
        NodeService nodeService=new NodeService();
        JsonResult jsonResult=new JsonResult();
        try {
            nodeService.updateNode(nodeid,nodename);
            jsonResult.setState(jsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(jsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());

        }
        renderJson(jsonResult,resp);
    }
}
