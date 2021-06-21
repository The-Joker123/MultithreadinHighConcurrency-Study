package com.zengzixi.juc01;

import java.util.concurrent.*;

public class T02_HowToCreateThread {
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("Hello MyThread!");
        }
    }
    static class MyRun implements Runnable{
        @Override
        public void run(){
            System.out.println("Hello MyRun");
        }

    }

    static class  MyCall implements Callable<String>{

        @Override
        public String call() throws Exception {//线程执行体,并且有返回值。
            System.out.println("Hello MyCall");
            return "Success";
        }
    }

    //启动线程的5种方式
    public  static  void main(String[] args){
        new MyThread().start();
        new Thread(new MyRun()).start();


        //........jdk8之前
        new Thread(new  Runnable(){
            @Override
            public  void run(){
                System.out.println("Hello---------!");
            }
        }).start();
        //Jdk8之后lambda表达式实现Runnable
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }
        ).start();
        //.......................

        FutureTask<String> ft = new FutureTask<String>(new MyCall());
        //使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程。
        //调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值
        Thread t=new Thread(new FutureTask<String>(new MyCall()));
        t.start();


        ExecutorService service= Executors.newCachedThreadPool();


        service.execute(()->{
            System.out.println("Hello ThreadPool");
        });
        service.shutdown();;
    }
}
