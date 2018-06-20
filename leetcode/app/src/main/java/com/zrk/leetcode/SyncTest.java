package com.zrk.leetcode;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/5/23 17:49 1.0
 * @time 2018/5/23 17:49
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/5/23 17:49
 */

public class SyncTest {

    static abstract class Tester {

        protected static Random random = new Random();

        protected int N = 1000;

        public Tester(int n) {
            N = n;
            writeValueArray = new long[N];
            threadPool = Executors.newFixedThreadPool(N * 2 + 1);
            barrier = new CyclicBarrier(N * 2 + 1);
        }

        protected long[] writeValueArray;
        private long readValue;

        private ExecutorService threadPool;

        private CyclicBarrier barrier;

        protected long value;

        private long duration;

        protected abstract void write(int index);

        protected abstract long read(int index);


        public void test() {
            long start = System.nanoTime();
            for (int i = 0; i < N; i++) {
                final int finalI = i;
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        write(finalI);
                        try {
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                });
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        readValue = read(finalI);
                        try {
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            duration = System.nanoTime() - start;
            barrier.reset();
        }

        public long getDuration() {
            return duration;
        }

    }


    static class SyncTester extends Tester {

        public SyncTester(int n) {
            super(n);
        }

        @Override
        protected synchronized void write(int index) {
            writeValueArray[index] = random.nextLong();
        }

        @Override
        protected synchronized long read(int index) {
            return writeValueArray[index];
        }
    }

    static class LockTester extends Tester {
        private Lock lock = new ReentrantLock();

        public LockTester(int n) {
            super(n);
        }

        @Override
        protected void write(int index) {
            lock.lock();
            try {
                writeValueArray[index] = random.nextLong();
            } finally {
                lock.unlock();
            }
        }

        @Override
        protected long read(int index) {
            lock.lock();
            try {
                return writeValueArray[index];
            } finally {
                lock.unlock();
            }
        }
    }

    static class BasicTester extends Tester {

        public BasicTester(int n) {
            super(n);
        }

        @Override
        protected void write(int index) {
            writeValueArray[index] = random.nextLong();
        }

        @Override
        protected long read(int index) {
            return writeValueArray[index];
        }
    }

    static class AtomicTester extends Tester {

        private AtomicLongArray writeAtomicArray;

        public AtomicTester(int n) {
            super(n);
            writeAtomicArray = new AtomicLongArray(N);
        }

        @Override
        protected void write(int index) {
            writeAtomicArray.set(index, random.nextLong());
        }

        @Override
        protected long read(int index) {
            return writeAtomicArray.get(index);
        }
    }


    public static void main(String[] a) {

        test(1000);
        System.out.println("--------------");
        System.gc();
        test(5000);
        System.out.println("--------------");
        System.gc();
        test(10000);
        System.out.println("--------------");


    }




    private static void test(int n) {
        BasicTester basicTester = new BasicTester(n);
        SyncTester syncTester = new SyncTester(n);
        LockTester lockTester = new LockTester(n);
        AtomicTester atomicTester = new AtomicTester(n);

        System.out.println("------------------------");
        System.out.println("basic test");
        basicTester.test();

        System.out.println("sync test");
        syncTester.test();

        System.out.println("lock test");
        lockTester.test();

        System.out.println("atomic test");
        atomicTester.test();


        System.out.println("-------compare------------");
        System.out.println("tester        " + basicTester.N);
        System.out.printf("basic        :%15d\n", basicTester.getDuration());
        System.out.printf("sync         :%15d\n", syncTester.getDuration());
        System.out.printf("lock         :%15d\n", lockTester.getDuration());
        System.out.printf("atom         :%15d\n", atomicTester.getDuration());
    }
}
