package com.ryan.thread;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:04 1.0
 * @time 2018/1/25 17:04
 * @project leetcode com.ryan.thread
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:04
 */

public class MainThreadService implements ThreadService {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public boolean run(Runnable task) {
        mHandler.post(task);
        return true;
    }
}
