package com.zrk.leetcode.easy.RomanToInteger;

import android.support.v4.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/12 14:11 1.0
 * @time 2018/1/12 14:11
 * @project leetcode com.zrk.leetcode.easy.RomanToInteger
 * @description Given a roman numeral, convert it to an integer.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * <p>
 * Ｉ、Ｖ、Ｘ、Ｌ、Ｃ、Ｄ和Ｍ，分别表示１、５、１０、５０、１００、５００和１０００
 * @updateVersion 1.0
 * @updateTime 2018/1/12 14:11
 */

public class Solution {
    public static void main(String[] args) {
        int i = new Solution().romanToInt("DCCLXXXII");
        System.out.println("romanToInt:" + i);
    }



    public int romanToInt(String s) {
        if (s == null || s.length() <= 0) return 0;
        int result = 0;
        int iNext = -1;
        for (int i = 0; i < s.length(); i++) {
            int i1 = iNext == -1 ? getRomanValue(s.charAt(i)) : iNext;
            if (i + 1 < s.length()) {
                int i2 = getRomanValue(s.charAt(i + 1));
                iNext = i2;
                if (i1 < i2) {
                    iNext = -1;
                    result += (i2 - i1);
                    i++;
                    continue;
                }
            } else {
                iNext = -1;
            }
            result += i1;
        }
        return result;
    }

    private static int getRomanValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}
