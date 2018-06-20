package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/20 16:42 1.0
 * @time 2018/6/20 16:42
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/20 16:42
 */

public class AB {

    public static void main(String[] args) {

        /**
         * method1
         */
        for (int i = 0; i < 81; i++) {
            if (i / 9 % 3 == i % 9 % 3) {
                continue;
            }
            System.out.println("A=" + (i / 9 + 1) + ",B=" + (i % 9 + 1));
        }

    }
}
