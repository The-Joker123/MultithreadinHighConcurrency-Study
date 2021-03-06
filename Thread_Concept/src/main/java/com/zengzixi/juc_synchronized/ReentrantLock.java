package com.zengzixi.juc_synchronized;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.locks.Lock;

public class ReentrantLock{
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类
        //@FunctionalInterface,函数式接口函数式
        //JDK1.8 lambda表达式（参数）->{代码}
        Test ticket=new Test();
        new Thread(()->{
            for (int i = 0; i <60 ; i++) {
                ticket.sale();
            }
        },"A" ).start();
        new Thread(()->{
            for (int i = 0; i <60 ; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <60 ; i++) {
                ticket.sale();
            }
        },"C").start();
    }

}
//lock 三部曲
//1.
class  Test{
    private  int number=50;
    Lock lock=new java.util.concurrent.locks.ReentrantLock();
    //本质排队
    //锁的是对象
    public /*synchronized*/   void  sale(){
        lock.lock();
        if(number>0){

            System.out.println(Thread.currentThread().getName()+"卖出了"
                    +(number--)+"票,剩余："+number);
        }
    }
}