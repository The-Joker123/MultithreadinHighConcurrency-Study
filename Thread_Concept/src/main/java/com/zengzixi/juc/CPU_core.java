package com.zengzixi.juc;

public class CPU_core {
    public static void main(String[] args) {
        //获取CPU的核数
        //CPU密集型，IO密集型
        //并发的编程的本质，充分利用CPU的资源
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
