package com.zrk.leetcode.medium.GroupAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/7 18:06 1.0
 * @time 2018/6/7 18:06
 * @project leetcode com.zrk.leetcode.medium.GroupAnagrams
 * @description Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * <p>
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * @updateVersion 1.0
 * @updateTime 2018/6/7 18:06
 */

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = hash(str);
            List<String> stringList = map.get(key);
            if (stringList == null) {
                stringList = new LinkedList<>();
            }
            stringList.add(str);
            map.put(key, stringList);
        }
        return new ArrayList<>(map.values());
    }

    private static String hash(String str) {
        if (str == null || str == "") {
            return "";
        }
        char[] result = new char[26];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            result[index]++;
        }
        return new String(result).intern();
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (String s : strs) {
            System.out.print(s + "   ");
        }

        List<List<String>> lists = new Solution().groupAnagrams(strs);

        for (List<String> list : lists) {
            System.out.println();
            for (String s : list) {
                System.out.print(s + "  ");
            }
        }
    }
}
