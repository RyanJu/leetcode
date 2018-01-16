package com.zrk.leetcode.easy.LongestCommonPrefix;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/15 10:21 1.0
 * @time 2018/1/15 10:21
 * @project leetcode com.zrk.leetcode.easy.LongestCommonPrefix
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/15 10:21
 */

public class Solution {

    public static void main(String[] ag) {
        String[] strs = {"abcde", "abcfgew", "abcsdsd", "abcmnn"};
        String prefix = new Solution().longestCommonPrefix(strs);
        System.out.println(prefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        StringBuilder result = new StringBuilder();
        int index = 0;
        boolean out = false;
        while (!out) {
            char c = 0;
            for (int i = 0; i < strs.length; i++) {
                if (index >= strs[i].length()) {
                    out = true;
                    break;
                }

                c = i == 0 ? strs[i].charAt(index) : c;
                if (strs[i].charAt(index) != c) {
                    out = true;
                    break;
                }
            }
            if (out)
                break;
            index++;
            result.append(c);
        }

        return result.toString();
    }
}
