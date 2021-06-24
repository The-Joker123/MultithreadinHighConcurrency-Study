package com.zengzixi.volatile_CAS;

import sun.security.jca.GetInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum  EnumSingle {
    INTANCE;//枚举元素

    public EnumSingle getInstance(){
        return INTANCE;
    }
}
class Test{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle instance1=EnumSingle.INTANCE;
        Constructor<EnumSingle> declaredConstructor=EnumSingle.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);//无视私有
        EnumSingle instance2= declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);

    }
}
