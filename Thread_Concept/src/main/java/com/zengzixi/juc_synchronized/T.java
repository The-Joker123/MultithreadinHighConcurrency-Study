package com.zengzixi.juc_synchronized;

import org.junit.Test;
import sun.awt.windows.ThemeReader;


public class T implements Runnable{

    private static int count = 100;
//    private Object o = new Object();
    @Override
    public /*synchronized*/ void run() {
        synchronized(this) { //任何线程想要执行那个下面的代码，必须先要拿到this的锁
            //synchronized(o) { //任何线程要想执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " +
                    count);
        }
    }
    public synchronized  void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start...");
        count--;
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName()+"m1 end...");
    }
    public  void m2(){

        count--;
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName()+"m2 ");
    }
     public static void mm(){
//        synchronized (T.class){  //考虑一下这里写synchronized(this)是否可以？
//           count --;
//        }

     }

    public static void main(String[] args) {
        T t=new T();
//        for (int i = 0; i <100 ; i++) {
//            new Thread(t,"小明"+i).start();
//            new Thread(t,"小张"+i).start();
//        }
        new Thread(t::m1,"小明").start();
         new Thread(t::m2,"小张").start();
    }
}

