package com.zengzixi.juc_synchronized;

import java.util.concurrent.TimeUnit;

/**
 * @author dange
 */
public class Account  {
    private String name;
    private double balance;
    public synchronized  void setBalance (String name,double balance){


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

    //--------------------------------------------------------
    public synchronized double getBalance() {

        return this.balance;
    }

    public static void main(String[] args) {
        Account a=new Account();
        new Thread(()->a.setBalance("小张",1000)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
    }
}
