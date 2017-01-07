package com.lsy.web.admin;

import com.lsy.dto.JsonResult;
import com.lsy.exception.ServiceException;
import com.lsy.service.NodeService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
@WebServlet("/admin/delNode")
public class DelNodeServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        NodeService nodeService=new NodeService();
        JsonResult jsonResult=new JsonResult();
        try{
            nodeService.delNodeById(id);
            jsonResult.setState(jsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(jsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJson(jsonResult,resp);
    }
}
