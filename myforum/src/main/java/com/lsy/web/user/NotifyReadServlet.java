package com.lsy.web.user;

import com.lsy.service.UserService;
import com.lsy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notifyRead")
public class NotifyReadServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids=req.getParameter("ids");
        UserService userService = new UserService();
        userService.updateNotifyStateByIds(ids);
        renderText("success",resp);

    }
}
