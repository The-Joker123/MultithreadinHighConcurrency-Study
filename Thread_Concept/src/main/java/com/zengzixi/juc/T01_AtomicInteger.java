//cas号称是无锁优化，或者叫自旋。这个名字无所谓，理解它是干什么的就行，概念这个东西是人为了
//描述问题解决问题而定义出来的，所以怎么定义不是很重要，重点是在解决问题上
//我们通过Atomic类（原子的）。由于某一些特别常见的操作，老是来回的加锁，加锁的情况特别多，所
//以干脆java就提供了这些常见的操作这么一些个类，这些类的内部就自动带了锁，当然这些锁的实现并
// 不是synchronized重量级锁，而是CAS的操作来实现的（号称无锁）。
// 我们来举例几个简单的例子，凡是以Atomic开头的都是用CAS这种操作来保证线程安全的这么一些个
//类。AtomicInteger的意思就是里面包了一个Int类型，这个int类型的自增 count++ 是线程安全的，还有
//拿值等等是线程安全的，由于我们在工作开发中经常性的有那种需求，一个值所有的线程共同访问它往
//上递增 ，所以jdk专门提供了这样的一些类。使用方法AtomicInteger如下代码




/**
 * 解决同样的问题的高效方法，使用AtomXXX类
 * AtomXXX类的本身方法都是原子性的，但不能保证多个方法连续调用都是原子性的
 * @author zengzixi
 */
package com.zengzixi.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {
       AtomicInteger count=new AtomicInteger(0);
       public void m(){
           for(int i=0;i<10000;i++){
               count.incrementAndGet();//增加并且得到
           }
       }

    public static void main(String[] args) {
        T01_AtomicInteger t=new T01_AtomicInteger();
        ArrayList<Thread> threads1 = new ArrayList<Thread>();
        List<Thread> threads = new ArrayList<Thread>();

        for(int i=0;i<10;i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }
        threads.forEach((o) -> o.start());
        threads.forEach((o)->{
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }

}
