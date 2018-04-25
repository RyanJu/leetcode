package com.ryan.thread;

import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:02 1.0
 * @time 2018/1/25 17:02
 * @project leetcode com.ryan.thread
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:02
 */

public interface ThreadService {
    boolean run(Runnable task);
}
