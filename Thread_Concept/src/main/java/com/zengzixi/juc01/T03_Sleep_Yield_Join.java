package com.zengzixi.juc01;

import org.junit.Test;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
        testJoin();
//        testYield();
    }
//---------------------------------------------------

    static void testSleep(){//进入阻塞状态，但是不释放锁🔒
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("A"+i);
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //-------------------------------------------------
    static  void testYield(){//Yield()礼让重新回到就绪状态
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("A"+i);
                if(i%10==0)Thread.yield();
            }

        }).start();
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("-------B"+i);
                if(i%10==0)Thread.yield();
            }

        }).start();
    }
//------------------------------------------------------
    static void testJoin(){
        Thread t1 = new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(5);
        //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            try {
                t1.join();//t2加入到t1线程后，t1先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0; i<100; i++) {
                System.out.println("B-----" + i);
                try {
                    Thread.sleep(500);
        //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
