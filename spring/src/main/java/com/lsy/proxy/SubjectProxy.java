package com.lsy.proxy;

/**
 * Created by Administrator on 2017/1/8 0008.
 */
public class SubjectProxy implements Subject {
    RelSubject relSubject=new RelSubject();

    @Override
    public void sayHello() {
        System.out.println("提交事务");
        try {
            relSubject.sayHello();
            System.out.println("提交事务");
        }catch (Exception e){
            System.out.println("回滚事务");
        }

    }

    @Override
    public void save() {

    }
}
