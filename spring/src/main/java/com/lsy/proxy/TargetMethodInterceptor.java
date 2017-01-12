package com.lsy.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class TargetMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("before....");
        Object result=methodProxy.invokeSuper(target,params);//目标对象方法的执行
        System.out.println("after...");
        return result;
    }
}
