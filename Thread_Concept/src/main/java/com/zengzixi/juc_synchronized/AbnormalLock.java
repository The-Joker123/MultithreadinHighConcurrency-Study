package com.zengzixi.juc_synchronized;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class AbnormalLock {
    private int count=0;
    public  void add(){

        System.out.println(Thread.currentThread().getName()+"start");
        while (true){
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+count);
            if(count==20){
                int i=1/0;
                System.out.println(Thread.currentThread().getName()+"end");
            }

        }
    }

    public static void main(String[] args) {
        AbnormalLock abnormalLock=new AbnormalLock();
        new Thread(abnormalLock::add,"第一个线程").start();//第一个线程出现异常，释放锁，第二个线程进入
        new Thread(abnormalLock::add,"第二个线程").start();
    }

}
