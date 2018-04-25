package com.zrk.leetcode;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/25 9:57 1.0
 * @time 2018/4/25 9:57
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/4/25 9:57
 */

public enum Singleton {
    INSTANCE;

    public static Singleton newInstance() {
        return INSTANCE;
    }


    private String mName;

    private Singleton() {
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }
}
