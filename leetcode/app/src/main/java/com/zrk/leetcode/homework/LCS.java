package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/6 17:53 1.0
 * @time 2018/6/6 17:53
 * @project leetcode com.zrk.leetcode.homework
 * @description 最长公共子序列问题，动态规划
 * @updateVersion 1.0
 * @updateTime 2018/6/6 17:53
 */

public class LCS {

    public int solution(char[] s1, char[] s2, StringBuilder lcs) {
        if (s1 == null || s2 == null)
            return 0;
        if (s1.length <= 0 || s2.length <= 0) {
            return 0;
        }

        int[][] nm = new int[s1.length][s2.length];
        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                nm[i][j] = -1;
            }
        }
        int[][] b = new int[s1.length][s2.length];

        int result = help(s1, s2, nm, s1.length - 1, s2.length - 1, b);

        print(s1, s2, b, lcs);
        return result;
    }

    private StringBuilder print(char[] s1, char[] s2, int[][] b, StringBuilder lcs) {
        lcs.delete(0,lcs.length());
        int i = s1.length - 1;
        int j = s2.length - 1;
        while (i >= 0 && j >= 0) {
            if (b[i][j] == 1) {
                lcs.insert(0, s1[i]);
                i--;
                j--;
            } else if (b[i][j] == 2) {
                i--;
            } else if (b[i][j] == 3) {
                j--;
            } else {
                throw new RuntimeException("b[" + i + "]" + "[" + j + "]=" + b[i][j]);
            }
        }
        return lcs;
    }

    private int help(char[] s1, char[] s2, int[][] nm, int i, int j, int[][] b) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (nm[i][j] >= 0) {
            return nm[i][j];
        }
        if (s1[i] == s2[j]) {
            nm[i][j] = help(s1, s2, nm, i - 1, j - 1, b) + 1;
            b[i][j] = 1;
        } else {
            int t1 = help(s1, s2, nm, i - 1, j, b);
            int t2 = help(s1, s2, nm, i, j - 1, b);
            if (t1 >= t2) {
                nm[i][j] = t1;
                b[i][j] = 2;
            } else {
                nm[i][j] = t2;
                b[i][j] = 3;
            }
        }
        return nm[i][j];
    }


    public static void main(String[] args) {
        String s1 = "ABCBDABBCSCHSKHSBCSI";
        String s2 = "BDCABAYUOIHUYGBCHSBDCSBDCSJKHCSBNCMSBDCSDJCKSCBSBBSDCBSBSHHJDBMHJCSD";
        StringBuilder lcs = new StringBuilder();
        System.out.println(new LCS().solution(s1.toCharArray(), s2.toCharArray(), lcs));
        System.out.println(lcs.toString());
    }
}
