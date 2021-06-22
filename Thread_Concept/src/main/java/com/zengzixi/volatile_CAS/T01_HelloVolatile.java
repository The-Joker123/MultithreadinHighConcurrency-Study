package com.zengzixi.volatile_CAS;

import java.util.concurrent.TimeUnit;

//t1线程进去了，并且一直在循环中，但是running的值已经修改，但是t1线程并不知道，
//volatile就怎么加数据的可见性，让t1线程发现runing已经被修改了。
public class T01_HelloVolatile {

   volatile boolean running = true;
    void m(){
        System.out.println("start");
        while (running){

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t01_helloVolatile=new T01_HelloVolatile();
        new Thread(()->t01_helloVolatile.m(),"t1").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(()->t01_helloVolatile.m(false),"t1").start();
        t01_helloVolatile.running=false;

    }
}
