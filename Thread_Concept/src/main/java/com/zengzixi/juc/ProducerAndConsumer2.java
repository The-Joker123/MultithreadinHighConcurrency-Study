package com.zengzixi.juc;

import com.oracle.jrockit.jfr.Producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class ProducerAndConsumer2 {

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
        Lock lock=new ReentrantLock();
        Condition condition = lock.newCondition();

        public  void add() throws InterruptedException {
            lock.lock();
            try{
                while (number!=0){
                    condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName()+number);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

//            if(number!=0){
//                this.wait();
//
//            }



        }
        public synchronized void decrease() throws InterruptedException {
            lock.lock();
            try{
                while (number==0){
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName()+number);
                  condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

}
