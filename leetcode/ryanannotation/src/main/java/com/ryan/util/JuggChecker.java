package com.ryan.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 15:26 1.0
 * @time 2018/1/25 15:26
 * @project leetcode com.ryan.util
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 15:26
 */

public class JuggChecker {
    public static <T extends Collection> boolean CheckEmpty(T list) {
        return list == null || list.isEmpty();
    }

    public static <T extends Object> boolean CheckEmpty(T[] objects) {
        return objects == null || objects.length <= 0;
    }

    public static boolean CheckEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean CheckEmpty(StringBuilder builder) {
        return builder == null || builder.length() <= 0;
    }

    public static boolean CheckEmpty(StringBuffer buffer) {
        return buffer == null || buffer.length() <= 0;
    }

    public static boolean CheckEmpty(int[] objects) {
        return objects == null || objects.length <= 0;
    }

    public static boolean CheckEmpty(float[] objects) {
        return objects == null || objects.length <= 0;
    }

    public static boolean CheckEmpty(double[] objects) {
        return objects == null || objects.length <= 0;
    }

}
