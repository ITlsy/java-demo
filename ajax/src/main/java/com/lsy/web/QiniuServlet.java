package com.lsy.web;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/qiniu")
public class QiniuServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置好账号的ACCESS_KEY和SECRET_KEY
        String ak="66HBb2Px7SG8Y8_xyrQPIFn2aOyuxY1-jeMT0Ya2";
        String sk="ZXozXb4EspO-2BMV2fur1EWCL6OM9XcdqMY-1UUs";
        //要上传的空间
        String bucketName="store";
        //密钥配置
        Auth auth=Auth.create(ak,sk);
        StringMap map=new StringMap();
        map.put("returnUrl","http://localhost/qiniucallback");
        //token
       // String token=auth.uploadToken(bucketName);
        String token=auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("qiniu.jsp").forward(req,resp);

    }
}
