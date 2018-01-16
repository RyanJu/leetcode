package com.zrk.leetcode.medium.LongestPalingdromicSubString;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/9 14:00 1.0
 * @time 2018/1/9 14:00
 * @project leetcode com.zrk.leetcode.medium.LongestPalingdromicSubString
 * @description Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 * @updateVersion 1.0
 * @updateTime 2018/1/9 14:00
 */

public class Solution {

    public static void main(String[] args) {
        String s = new Solution().longestPalindrome("bb");
        System.out.println(s);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;
        int start = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            int k = i;
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                if (k - j + 1 > max) {
                    max = k - j + 1;
                    start = j;
                }
                k++;
                j--;
            }


            j = i;
            k = i + 1;
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                if (k - j + 1 > max) {
                    max = k - j + 1;
                    start = j;
                }
                k++;
                j--;
            }
        }
        return s.substring(start, start + max);

    }
}
