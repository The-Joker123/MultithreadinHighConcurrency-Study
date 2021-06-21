package com.zengzixi.juc_synchronized;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.TimeUnit;

class ReentrantLock {

    synchronized void m(){
        System.out.println("父类");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }
    public static void main(String[] args) {
        new TT().m();//子类先拿到了锁，所以锁的是子类对象
    }

}

class TT extends  ReentrantLock{
    @Override

    synchronized void  m(){
        System.out.println("子类");
        super.m();
        System.out.println("子类1");
    }


}

