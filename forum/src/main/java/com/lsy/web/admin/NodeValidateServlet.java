package com.lsy.web.admin;

import com.lsy.service.NodeService;
import com.lsy.util.StringUtils;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
@WebServlet("/admin/nodeValidate")
public class NodeValidateServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid=req.getParameter("nodeid");
        String nodename=req.getParameter("nodename");
        nodename= StringUtils.isTouf8(nodename);
        NodeService nodeService=new NodeService();
       String result=nodeService.validateNodename(nodeid,nodename);
       renderText(result,resp);
    }
}
