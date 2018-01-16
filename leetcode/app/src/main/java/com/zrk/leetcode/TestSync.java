package com.zrk.leetcode;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/15 16:22 1.0
 * @time 2018/1/15 16:22
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/15 16:22
 */

public class TestSync implements Runnable{
    int b = 100;

    synchronized void m1() throws InterruptedException {
        System.out.println("m1");
        b = 1000;
//        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("m2");
//        Thread.sleep(250); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync tt = new TestSync();
        Thread t = new Thread(tt);  //1
        t.start(); //2

        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
