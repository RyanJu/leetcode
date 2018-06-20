package com.zrk.leetcode;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/5/25 11:34 1.0
 * @time 2018/5/25 11:34
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/5/25 11:34
 */

public class MultiInstance {

    private static int c = 0;

    private int id = c++;

    public String toString() {
        return MultiInstance.class.getSimpleName() + "-" + id;
    }

    private static class Pool {

        private Semaphore mSemaphore;
        private int mCount;
        private boolean[] mUsed;
        private MultiInstance[] mInstances;

        private Pool() {
            mCount = 4;
            mSemaphore = new Semaphore(mCount);
            mUsed = new boolean[mCount];
            mInstances = new MultiInstance[mCount];
        }

        public MultiInstance get() throws InterruptedException {
            mSemaphore.acquire();
            for (int i = 0; i < mCount; i++) {
                if (!mUsed[i]) {
                    synchronized (this) {
                        if (!mUsed[i]) {
                            MultiInstance instance = mInstances[i];
                            if (instance == null) {
                                instance = new MultiInstance();
                                mInstances[i] = instance;
                            }
                            mUsed[i] = true;
                            return instance;
                        }
                    }
                }
            }
            return null;
        }

        public void markUnused(MultiInstance instance) {
            if (instance == null) {
                return;
            }
            for (int i = 0; i < mCount; i++) {
                if (instance == mInstances[i]) {
                    if (mUsed[i]) {
                        mUsed[i] = false;
                        mSemaphore.release();
                    }
                }
            }
        }

        static class Builder {
            static Pool POOL = new Pool();
        }

    }

    public static MultiInstance use() {
        try {
            return Pool.Builder.POOL.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void release() {
        Pool.Builder.POOL.markUnused(this);
    }

    private MultiInstance() {
    }


    public static void main(String[] args) throws InterruptedException, IOException, BrokenBarrierException {
        ExecutorService exec = Executors.newCachedThreadPool();
        int taskCount = 10;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(taskCount + 1);
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ready for start!");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    MultiInstance instance = MultiInstance.use();
                    System.out.println(Thread.currentThread().getName() + " " + instance);
                    instance.release();
                }
            });
        }


        System.out.println("Enter any character to start!");
        System.in.read();
        cyclicBarrier.await();

    }


}
