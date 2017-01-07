package com.lsy.web.admin;

import com.lsy.dto.JsonResult;
import com.lsy.exception.ServiceException;
import com.lsy.service.UserService;
import com.lsy.util.Page;
import com.lsy.util.StringUtils;
import com.lsy.vo.UserVo;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
@WebServlet("/admin/user")
public class AdminUserServlet extends BaseServlet {
    UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p=req.getParameter("p");
        Integer pageNo= StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        Page<UserVo> page=userService.findUserList(pageNo);
        req.setAttribute("page",page);
        forward("/admin/user.jsp",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //jquery rel 方式获取
         String userStateId = req.getParameter("userStateId");
        String[] stateAndId = userStateId.split(",");
        String userid = stateAndId[1];
        Integer userState = Integer.valueOf(stateAndId[0]);
        //js方式获取
        /*Integer userState = Integer.valueOf(req.getParameter("userState"));
        String userid=req.getParameter("userid");*/

        userState=userState == 1 ? 2:1;
        JsonResult jsonResult=new JsonResult();
        try{
            userService.updateUserState(userid,userState);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }

        renderJson(jsonResult,resp);
    }
}
