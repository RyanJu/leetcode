package com.zrk.leetcode.easy.PalindromeNumber;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/11 9:57 1.0
 * @time 2018/1/11 9:57
 * @project leetcode com.zrk.leetcode.easy.PalindromeNumber
 * @description Determine whether an integer is a palindrome. Do this without extra space.
 * @updateVersion 1.0
 * @updateTime 2018/1/11 9:57
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(-123321));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x >= 0 && x < 10) return true;
        int a = Math.abs(x);
        int reverse = 0;
        while (a > 0) {
            reverse = reverse * 10 + a % 10;
            a /= 10;
        }
        return reverse == Math.abs(x);
    }
}
