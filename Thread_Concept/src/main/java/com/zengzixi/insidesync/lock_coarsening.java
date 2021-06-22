package com.zengzixi.insidesync;

/*JVM 会检测到这样一连串的操作都对同一个对象加锁（while 循环内 100 次执行 append，
 没有锁粗化的就要进行 100  次加锁/解锁），
 此时 JVM 就会将加锁的范围粗化到这一连串的操作的外部（比如 while 虚幻体外），
 使得这一连串操作只需要加一次锁即可。*/

public class lock_coarsening {
    public String test(String str){

        int i = 0;
        StringBuffer sb = new StringBuffer();
        while(i < 100){
            sb.append(str);
            i++;
        }
        return sb.toString();
    }
}
