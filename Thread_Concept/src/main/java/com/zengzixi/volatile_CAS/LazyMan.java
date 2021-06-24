package com.zengzixi.volatile_CAS;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

//懒汉式单例
public class LazyMan {

private static  boolean zzx=false;
    private  LazyMan(){
        synchronized (LazyMan.class){
            if(zzx==false){
                zzx=true;
            }else
            {
                throw  new RuntimeException("不要使用反射来破环单例");
            }
            System.out.println(Thread.currentThread().getName());
        }

    }
    private volatile static LazyMan lazyMan;
//双重检测
    public static LazyMan getInstance(){
        if(lazyMan==null){
           synchronized(LazyMan.class){
               if(lazyMan==null){
                    lazyMan=new LazyMan();//不是原子性操作
                   /*
                   1.分配内存空间
                   2.执行构造方法，初始化对象
                   3.把这个对象指向这个空间
                    */
                }
            }
        }
        return lazyMan;//其他线程会造成空指针
    }

    public static void main(String[] args) throws Exception{

//        for (int i = 0; i <100 ; i++) {
//            new Thread(()->getInstance()).start();
//
//
//        }



        //反射机制调用
        Constructor<LazyMan> declaredConstructor=LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);//无视私有
       LazyMan instance2= declaredConstructor.newInstance();

        TimeUnit.SECONDS.sleep(2);
//        LazyMan instance= declaredConstructor.newInstance();
        LazyMan instance=LazyMan.getInstance();//单例模式调用
        System.out.println(instance);
        System.out.println(instance2);
    }
}
