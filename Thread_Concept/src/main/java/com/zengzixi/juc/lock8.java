package com.zengzixi.juc;

import java.util.concurrent.TimeUnit;

public class lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 =new Phone();
        Phone phone2 =new Phone();

        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone2.call();
        },"B").start();
    }




}
class Phone{
    //synchronized 锁的对象是方法的调用者！
    //两个方法用的是同一个锁，谁先拿到谁执行
    // static 锁的是class
    public  static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public static synchronized void call(){
        System.out.println("call");
    }
    //这里没有锁！不是同步方法，不受锁的影响
    public  void hello(){
        System.out.println("hello");
    }

}
