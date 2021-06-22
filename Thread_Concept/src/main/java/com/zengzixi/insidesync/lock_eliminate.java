package com.zengzixi.insidesync;
//我们都知道 StringBuffer 是线程安全的，因为它的关键方法都是被 synchronized 修饰过的，但我们看上面这段代码，我们会发现，
//sb 这个引用只会在 add 方法中使用，不可能被其它线程引用（因为是局部变量，栈私有），因此 sb 是不可能共享的资源，
//JVM 会自动消除 StringBuffer 对象内部的锁。

public class lock_eliminate {
    public void add(String str1,String str2){
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }
}
