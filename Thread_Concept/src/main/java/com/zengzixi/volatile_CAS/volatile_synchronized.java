package com.zengzixi.volatile_CAS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class volatile_synchronized {
    //volatile 不保证原子性
 private  volatile static AtomicInteger num=new AtomicInteger();
    public  static void add(){

        num.getAndIncrement();
//            count++;//不是一个原子性操作
            //获取值
            //+1
            //写回这个值
    }
    public static void main(String[] args) {
        for (int i = 0; i <20 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(volatile_synchronized.num);
    }
}
