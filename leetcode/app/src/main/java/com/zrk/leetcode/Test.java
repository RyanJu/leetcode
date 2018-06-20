package com.zrk.leetcode;

import android.app.IntentService;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.telephony.PhoneNumberUtils;
import android.util.Size;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TimeUtils;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PushbackReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.transform.Source;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/13 14:12 1.0
 * @time 2018/4/13 14:12
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/4/13 14:12
 */

public class Test {
    static class Art {
        Art() {
            System.out.println("art constructor ");
        }

        Art(int i) {
            System.out.println("art constructor " + i);
        }
    }

    static class Painting extends Art {
        Painting() {
            System.out.println("painting constructor ");
        }

        private void paint() {
            System.out.println("Painting.paint");
        }
    }

    static class Cartoon extends Painting {
        Cartoon(int c) {
            System.out.println("Cartoon constructor " + c);
        }

        public void paint() {
            System.out.println("Cartoon.paint");
        }
    }


    public static class Parent {
        int age;
        String name;

        @Override
        public String toString() {
            return "Parent{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        protected Object clone() {
            Parent copy = new Parent();
            copy.age = this.age;
            copy.name = this.name;
            return copy;
        }
    }

    public static class Son extends Parent {
        public class Inner {
        }
    }

    static class InnerHerit extends Son.Inner {
        InnerHerit(Son son) {
            son.super();
        }
    }


    static class A {
        A() {
            System.out.println("A constructor");
            draw();
            System.out.println("A constructor end");
        }

        void draw() {
            System.out.println("draw A");
        }

        Object process() throws IOException {
            return new Object();
        }

    }

    static class B extends A {
        Object object = new Object();

        B() {
            System.out.println("B constructor");
            draw();
            System.out.println("B constructor end");
        }

        void draw() {
            System.out.println("draw B " + object);
        }

        String process() {
            return "string";
        }
    }

    public static class F {
        static {
            System.out.println("loading F");
        }
    }

    public static class T extends F {
        static int anInt = 1;

        static {
            System.out.println("loading T");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {
        LockIncrement lockIncrement = new LockIncrement();
        SynchronizingIncrement synchronizingIncrement = new SynchronizingIncrement();
        long t1 = Incrementable.test(synchronizingIncrement);
        long t2 = Incrementable.test(lockIncrement);
        System.out.println("synchronizing " + t1);
        System.out.println("locking " + t2);
        System.out.println("times: " + t2 / t1);
    }

    static abstract class Incrementable {
        protected long counter = 0;

        public abstract void increment();

        public static long test(Incrementable incrementable) {
            long t = System.nanoTime();
            for (long i = 0; i < 10000000L; i++) {
                incrementable.increment();
            }
            return System.nanoTime() - t;
        }
    }

    static class LockIncrement extends Incrementable {
        private Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            lock.lock();
            try {
                ++counter;
            } finally {
                lock.unlock();
            }
        }
    }

    static class SynchronizingIncrement extends Incrementable {

        @Override
        public synchronized void increment() {
            ++counter;
        }
    }

    private static void testCountDownLatch() throws IOException {
        int SIZE = 10;
        final CountDownLatch startCDL = new CountDownLatch(1);
        final CountDownLatch cdl = new CountDownLatch(SIZE);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " waiting to start!");
                        startCDL.await();
                        Thread.sleep(new Random().nextInt(3000));
                        System.out.println(Thread.currentThread().getName() + " things done!");
                        cdl.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdl.await();
                    System.out.println("all count down !");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Enter to start");
        System.in.read();
        startCDL.countDown();
        threadPool.shutdown();
    }

    private static void testPipe() throws IOException {
        final PipedWriter producer = new PipedWriter();
        final PipedReader consumer = new PipedReader(producer);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " produce " + i);
                        producer.write(i);
                        Thread.sleep(500);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        int i = consumer.read();
                        System.out.println(Thread.currentThread().getName() + " consume " + i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


    private static void testBlockingQueue() {
        final BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Producer(queue));
        threadPool.execute(new Consumer(queue));
    }

    private static class Producer implements Runnable {

        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " produce " + i);
                    queue.put(i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Integer integer = queue.take();
                    System.out.println(Thread.currentThread().getName() + " consume " + integer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testInterrupt() throws InterruptedException {

        final Object lock = new Object();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait lock");
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + " get lock ");

                    }
                    while (true) {

                    }
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted !");
                }

                System.out.println(Thread.currentThread().getName() + " end");
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        synchronized (lock) {
            System.out.println("main get lock");
            Thread.sleep(5000);
        }
    }


    private static void testString() {
        int COUNT = 100000;

        long t1 = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < COUNT; i++) {
            result += String.valueOf(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("first " + (t2 - t1));
        long t3 = System.currentTimeMillis();
        result = "";
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < COUNT; i++) {
            stringBuilder.append(String.valueOf(i));
        }
        result = stringBuilder.toString();
        long t4 = System.currentTimeMillis();
        System.out.println("second " + (t4 - t3));

    }

    private static Object invokeNewInstance(String classPath) {
        try {
            Class clz = Class.forName(classPath);
            Constructor constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
