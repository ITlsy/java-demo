package com.lsy.proxy;

/**
 * Created by Administrator on 2017/1/8 0008.
 */
public class RelSubject implements Subject {
    @Override
    public void sayHello() {
        System.out.println("RealSubject sayHello...");
    }

    @Override
    public void save() {
        System.out.println("RealSubject save...");

    }
}
