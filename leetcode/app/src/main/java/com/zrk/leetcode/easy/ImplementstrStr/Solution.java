package com.zrk.leetcode.easy.ImplementstrStr;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/8 16:48 1.0
 * @time 2018/4/8 16:48
 * @project leetcode com.zrk.leetcode.easy.ImplementstrStr
 * @description Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * @updateVersion 1.0
 * @updateTime 2018/4/8 16:48
 */

public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null) {
            return -1;
        }
        if (haystack.equals(needle)) {
            return 0;
        }

        for (int i = 0, size = haystack.length(); i < size; i++) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
        }

        return -1;
    }
}
