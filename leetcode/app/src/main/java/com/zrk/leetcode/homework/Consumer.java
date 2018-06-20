package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/1 10:08 1.0
 * @time 2018/6/1 10:08
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/1 10:08
 */

public interface Consumer<T> {
    boolean consume(T data);
}
