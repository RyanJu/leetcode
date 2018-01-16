package com.zrk.leetcode.medium.IntegerToRoman;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/12 14:54 1.0
 * @time 2018/1/12 14:54
 * @project leetcode com.zrk.leetcode.medium.IntegerToRoman
 * @description Given an integer, convert it to a roman numeral.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Ｉ、   Ｖ、  Ｘ、   Ｌ、     Ｃ、      Ｄ和      Ｍ，分别表示
 * １、   ５、  １０、 ５０、 １００、    ５００和１０００
 * @updateVersion 1.0
 * @updateTime 2018/1/12 14:54
 */

public class Solution {


    public static void main(String[] args) {
        String s = new Solution().intToRoman(782);
        System.out.println(s);
    }

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int divider = 1000;
        while (num > 0) {
            int i = num / divider;
            char roman = getUnit(divider);
            if (i > 0 && i < 4) {
                for (int j = 0; j < i; j++) {
                    result.append(roman);
                }
            } else if (i == 4) {
                result.append(roman).append(getUnit(divider * 5));
            } else if (i >= 5 && i < 9) {
                result.append(getUnit(divider * 5));
                for (int j = 0; j < i - 5; j++) {
                    result.append(roman);
                }
            } else if (i == 9) {
                result.append(roman).append(getUnit(divider * 10));
            }
            num %= divider;
            divider /= 10;
        }
        return result.toString();
    }

    private char getUnit(int iUnit) {
        switch (iUnit) {
            case 1000:
                return 'M';
            case 500:
                return 'D';
            case 100:
                return 'C';
            case 50:
                return 'L';
            case 10:
                return 'X';
            case 5:
                return 'V';
            case 1:
                return 'I';
        }
        return 0;
    }
}
