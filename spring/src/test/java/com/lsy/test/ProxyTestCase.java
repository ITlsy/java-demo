package com.lsy.test;

import com.lsy.proxy.*;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/1/8 0008.
 */
public class ProxyTestCase {
    public void proxy(){
        Subject subject=new SubjectProxy();
        subject.sayHello();
    }

    @Test
    public  void jdkProxy(){
        RelSubject relSubject=new RelSubject();
        SubjectInvocationHandler subjectInvocationHandler=new SubjectInvocationHandler(relSubject);
        Subject subject= (Subject) Proxy.newProxyInstance(relSubject.getClass().getClassLoader(),relSubject.getClass().getInterfaces(),subjectInvocationHandler);
        subject.sayHello();
        subject.save();
    }

    @Test
    public void cglibProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(new TargetMethodInterceptor());

        enhancer.setSuperclass(Target.class);
        Target target= (Target) enhancer.create();//父类指向子类对象

    }
}
