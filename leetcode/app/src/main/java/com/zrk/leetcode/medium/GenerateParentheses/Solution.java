package com.zrk.leetcode.medium.GenerateParentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/17 10:17 1.0
 * @time 2018/1/17 10:17
 * @project leetcode com.zrk.leetcode.medium.GenerateParentheses
 * @description Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * @updateVersion 1.0
 * @updateTime 2018/1/17 10:17
 */

public class Solution {

    public static void main(String[] args) {
        int n = 12;
        Solution solution = new Solution();
        long t1 = System.currentTimeMillis();
        List<String> strings = solution.generateParenthesis2(n);
        System.out.println("time=" + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        List<String> strings1 = solution.generateParenthesis(n);
        System.out.println("time=" + (System.currentTimeMillis() - t1));

//        for (String s : strings) {
//            System.out.println(s);
//        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if (n <= 0)
            return list;
        Map<String, Integer> openMap = new HashMap<>();
        Map<String, Integer> closeMap = new HashMap<>();
        list.add("(");
        openMap.put("(", 1);
        closeMap.put("(", 0);

        String str1;
        String str2;
        List<String> temp = new ArrayList<>();
        for (int i = 1; i < n * 2; i++) {
            temp.clear();
            for (String str : list) {
                int open =  openMap.get(str) ;
                int close =  closeMap.get(str) ;
                if (open < n) {
                    str1= (str + "(");
                    temp.add(str1);
                    openMap.put(str1, open + 1);
                    closeMap.put(str1, close);
                }
                if (close < open) {
                    str2 = (str + ")");
                    temp.add(str2);
                    closeMap.put(str2, close + 1);
                    openMap.put(str2, open);
                }
            }
            list.clear();
            list.addAll(temp);
        }
        return list;
    }


    /**
     * 递归方法，很巧妙
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<String>();
        recurse(list, "", 0, 0, n);
        return list;
    }

    /**
     * 只有右括号的个数小于左括号的个数时，才能添加一个右括号
     *
     * @param list
     * @param str
     * @param open
     * @param close
     * @param max
     */
    public void recurse(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }
        if (open < max) {
            recurse(list, str + "(", open + 1, close, max);
        }

        //这里close<open最关键，保证了右括号和左括号的匹配
        if (close < open) {
            recurse(list, str + ")", open, close + 1, max);
        }
    }


}
