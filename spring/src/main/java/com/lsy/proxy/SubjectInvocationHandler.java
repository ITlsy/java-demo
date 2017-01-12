package com.lsy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/8 0008.
 */
public class SubjectInvocationHandler implements InvocationHandler {
    private Object target;
    public SubjectInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())){
            Object result=null;
            try {
                System.out.println("before....");
                //代表目标对象方法的执行
                 result= method.invoke(target, args);
                System.out.println("after...");
            }catch (Exception e){
                System.out.println("exception...");

            }
            return result;
        }
        return method.invoke(target,args);
    }
}
