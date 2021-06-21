package com.zengzixi.juc01;

public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        Thread t = new MyThread();
//怎么样得到这个线程的状态呢？就是通过getState()这个方法
        System.out.println(t.getState());//他是一个new状态
        t.start();//到这start完了之后呢是Runnable的状态
        //Runnable内部有两个状态(1)Ready就绪状(2)Running运行状态.
        //就绪状态是说扔到CPU的等待队列里面去排队等待CPU运行，等真正扔到
        //CPU上去运行的时候才叫Running运行状态。
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//然后join之后，结束了是一个Timenated状态
        System.out.println(t.getState());
    }
}
