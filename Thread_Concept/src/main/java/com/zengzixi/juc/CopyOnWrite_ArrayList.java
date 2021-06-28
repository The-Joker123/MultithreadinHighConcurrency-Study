package com.zengzixi.juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWrite_ArrayList {
    public static void main(String[] args) {
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
//        List<String> list = new ArrayList<>();

        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();

        }

    }
}
