package com.lsy.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class HelloAction extends ActionSupport implements SessionAware,
        ServletRequestAware,ServletResponseAware,ApplicationAware,ServletContextAware{
    private String code;
     private Map<String,Object> session;
    public String execute(){
       // System.out.println("struts!");
        /*
        * 获取Session
        */
        /*//1.通过ActionContext
        ActionContext actionContext=ActionContext.getContext();
        Map<String,Object> session=actionContext.getSession();
        session.put("session","s1");*/
       /* //2.实现org.apache.struts2.interceptor.SessionAware接口
        session.put("session","s2");*/

       /*
       *HttpServletRequest和  HttpServletResponse获取
       */
      /* //1.通过ServletActionContext获取
        HttpServletRequest request= ServletActionContext.getRequest();
       *//* HttpSession session=request.getSession();
        session.getServletContext()*//*
        HttpServletResponse response=ServletActionContext.getResponse();
        //2.通过Aware接口获取*/

        /*
        * application对象
        */
        //1.通过ActionContext或ServletActionContext获取
        Map<String,Object> map=ActionContext.getContext().getApplication();
        ServletContext application=ServletActionContext.getServletContext();
        //2.通过Aware接口实现

        map.put("application","a1");
        return SUCCESS;
    }

    public String list(){
        code="1002";
        System.out.println("show!");
        return "success";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {

    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {

    }

    @Override
    public void setApplication(Map<String, Object> map) {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}
