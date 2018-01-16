package com.zrk.leetcode.medium.ZigZagConversion;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/11 9:19 1.0
 * @time 2018/1/11 9:19
 * @project leetcode com.zrk.leetcode.medium.ZigZagConversion
 * @description The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * @updateVersion 1.0
 * @updateTime 2018/1/11 9:19
 */

public class Solution {

    public static void main(String[] args) {
        String s = "PA";
        System.out.println(new Solution().convert(s, 2));
    }

    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        char[] result = new char[s.length()];
        int index = 0;
        int group = numRows * 2 - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j += group) {
                int a = j + i;
                int b = j + (group - i);
                if (b >= (j + group) || b == a) {
                    //only a
                    if (a < s.length()) {
                        result[index++] = s.charAt(a);
                    }
                } else {
                    // a and b
                    if (a < s.length()) {
                        result[index++] = s.charAt(a);
                    }
                    if (b < s.length()) {
                        result[index++] = s.charAt(b);
                    }
                }
            }
        }
        return new String(result);
    }
}
