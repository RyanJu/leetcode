package com.ryan.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:08 1.0
 * @time 2018/1/25 17:08
 * @project leetcode com.ryan.thread
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:08
 */

public class MultiThreadService implements ThreadService {
    private ExecutorService mMultiService = null;

    @Override
    public boolean run(Runnable task) {
        getExecutor().execute(task);
        return true;
    }

    public ExecutorService getExecutor() {
        return mMultiService == null ? mMultiService = Executors.newCachedThreadPool() : mMultiService;
    }
}
