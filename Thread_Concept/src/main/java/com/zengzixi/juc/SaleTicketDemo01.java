package com.zengzixi.juc;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类
        //@FunctionalInterface,函数式接口函数式
        //JDK1.8 lambda表达式（参数）->{代码}
        Ticket ticket=new Ticket();
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
class Ticket{
    private  int number=50;

    //本质排队
    //锁的是对象
    public synchronized   void  sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"
                    +(number--)+"票,剩余："+number);
        }
    }
}
