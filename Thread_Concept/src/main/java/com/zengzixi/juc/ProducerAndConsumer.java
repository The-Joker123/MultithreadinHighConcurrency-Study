package com.zengzixi.juc;

import com.oracle.jrockit.jfr.Producer;

import java.util.function.Consumer;

public class ProducerAndConsumer {

    public static void main(String[] args) {
      Data data=new Data();
        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.add();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"生产者").start();

        new Thread(()->{

            try {
                for (int i = 0; i <10 ; i++) {
                    data.decrease();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"消费者").start();
        new Thread(()->{

            try {
                for (int i = 0; i <10 ; i++) {

                    data.add();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"A").start();
        new Thread(()->{

            try {
                for (int i = 0; i <10 ; i++) {
                    data.decrease();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"B").start();
    }

    static class Data{
         int number=0;
        public synchronized void add() throws InterruptedException {
//            while (number!=0){
//                this.wait();
//
//            }
            if(number!=0){
                this.wait();

            }
            number++;
            System.out.println(Thread.currentThread().getName()+number);
            this.notifyAll();
        }
        public synchronized void decrease() throws InterruptedException {
//            while (number==0){
//                this.wait();
//
//            }
            //如果用if会造成虚假唤醒，A如果发现是0了，就会wait()释放锁
            if(number==0){
                this.wait();//线程休眠，并释放了锁
                System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getState());

            }
            number--;
            System.out.println(Thread.currentThread().getName()+number);

            this.notifyAll();//线程唤醒后执行wait()之后的代码，不再执行if,while
        }
    }

}
