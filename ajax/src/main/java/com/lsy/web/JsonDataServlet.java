package com.lsy.web;

import com.google.gson.Gson;
import com.lsy.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@WebServlet("/data.json")
public class JsonDataServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
       // String json="{\"id\":1,\"name\":\"jack\",\"address\":\"usa\"}";


        User user=new User(1,"niu","henan");
        User user1=new User(2,"dilan","beijing");
        User user2=new User(3,"hga","shanghai");

        List<User> userList = Arrays.asList(user,user1,user2);
        Gson gson=new Gson();
       // String json="{\"id\":1,\"name\"}";
        String json=gson.toJson(userList);
        PrintWriter out=resp.getWriter();
        //out.print("{\"id\":1,\"name\":\"李四\"}");
        out.print(json);
        out.flush();
        out.close();


    }
}
