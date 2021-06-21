package com.zengzixi.juc01;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {
    private static class T1 extends Thread{
        @Override
        public void run(){
            for (int i = 0; i <10 ; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("T1");
            }

        }
    }
    public static void main(String[] args) {
        //new T1().run();//这里只是调用了run()方法而已
        new T1().start();//启动线程，进入阻塞状态，但是不释放锁
        for (int i = 0; i <10 ; i++) {
            try{
                TimeUnit.MICROSECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("main");
            //第一次打印的肯定是main,因为main先运行
        }
    }
}



