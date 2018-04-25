package com.ryan.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:05 1.0
 * @time 2018/1/25 17:05
 * @project leetcode com.ryan.thread
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:05
 */

public class BackgroundThreadService implements ThreadService {
    private ExecutorService mSingleThreadService = null;

    @Override
    public boolean run(Runnable task) {
        getExecutor().execute(task);
        return true;
    }

    private ExecutorService getExecutor() {
        return mSingleThreadService == null ? mSingleThreadService = Executors.newSingleThreadExecutor()
                : mSingleThreadService;
    }
}
