package com.zrk.leetcode.medium.LongestSubstringWithoutRepeatingCharacters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/8 11:50 1.0
 * @time 2018/1/8 11:50
 * @project leetcode com.zrk.leetcode.medium.LongestSubstringWithoutRepeatingCharacters
 * @description Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * @updateVersion 1.0
 * @updateTime 2018/1/8 11:50
 */

public class Solution {

    public static void main(String[] args) {
        String word = "acbsdclsnlqjjlonnmsmmjusboqens";
        int i = new Solution().lengthOfLongestSubstring(word);
        System.out.println(i);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int length = s.length();
        char[] chars = s.toCharArray();
        int max = 0;
        int start = 0;
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if (map[c] == -1) {
                map[c] = i;
            } else {
                if (start < map[c] + 1) {
                    start = map[c] + 1;
                }
                map[c] = i;
            }
            if (i - start + 1 > max) {
                max = i - start + 1;
            }
        }
        return max;
    }
}
