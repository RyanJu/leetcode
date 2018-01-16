package com.zrk.leetcode.medium.LetterCombinationsofaPhoneNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/15 17:55 1.0
 * @time 2018/1/15 17:55
 * @project leetcode com.zrk.leetcode.medium.LetterCombinationsofaPhoneNumber
 * @description Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <p>
 * <p>
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * @updateVersion 1.0
 * @updateTime 2018/1/15 17:55
 */

public class Solution {

    public static void main(String[] args) {
        List<String> combinations = new Solution().letterCombinations("2");
        for (int i = 0; i < combinations.size(); i++) {
            System.out.println(combinations.get(i));
        }
    }


    public static final String[] qwers = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return result;
        int len = digits.length();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char c = digits.charAt(i);
            int n = c - '0';
            if (n != 0 && n != 1)
                words.add(qwers[n]);
        }

        recurseAddLetters(result, words, null, 0);
        return result;
    }

    private void recurseAddLetters(List<String> result, List<String> words, StringBuilder builder, int indexWord) {
        if (indexWord >= words.size()) {
            if (builder != null) {
                result.add(builder.toString());
            }
            return;
        }
        String word = words.get(indexWord);

        for (int i = 0; i < word.length(); i++) {
            StringBuilder thisBuilder = builder;
            if (thisBuilder == null) {
                thisBuilder = new StringBuilder();
            }
            thisBuilder.append(word.charAt(i));
            recurseAddLetters(result, words, thisBuilder, indexWord+1);
            thisBuilder.deleteCharAt(thisBuilder.length()-1);
        }
    }


}
