package com.zrk.leetcode;

/**
 * Created by zhurongkun on 2017/9/22.
 */

import android.support.annotation.IntRange;

/**
 * Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".
 * <p>
 * On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.
 * <p>
 * You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.
 * <p>
 * Example:
 * <p>
 * Input:
 * s1="acb", n1=4
 * s2="ab", n2=2
 * <p>
 * Return:
 * 2
 */
public class CountTheRepetitions {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        //do hash
        char[] hash = new char[26];
        char[] charArray = s2.toCharArray();
        for (char c : charArray) {
            hash[c - 'a'] = 1;
        }

        int i = 0;
        int j = 0;
        for (; i < s1.length() * n1; i++) {
            if (i % s1.length() == 0 && j % s2.length() == 0 && j >= s2.length()) break;
            char c1 = s1.charAt(i % s1.length());
            if (hash[c1 - 'a'] == 0) continue;
            char c2 = s2.charAt(j % s2.length());
            if (c1 == c2) {
                j++;
            }
        }

        System.out.println("i=" + i + " j=" + j + " ni=" + i / s1.length() + " nj=" + j / s2.length());

        if (j >= s2.length()) {
            //s2 can be sub sequence of [s1,n1]
            int x1 = i / s1.length();
            int x2 = j / s2.length();
            return (int) ((float) n1 / x1 * x2 / n2);
        } else {
            return 0;
        }

    }

    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        int sum = 0;
        for (int i = 0, j = 0; i < s1.length() * n1; i++) {
            char c1 = s1.charAt(i % s1.length());
            char c2 = s2.charAt(j);
            if (c1 == c2) {
                j++;
                if (j >= s2.length()) {
                    sum++;
                    j = 0;
                }
            }
        }
        return sum / n2;
    }
}
