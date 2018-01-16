package com.zrk.leetcode.MaximumLengthOfPairChain;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/5 11:15 1.0
 * @time 2018/1/5 11:15
 * @project leetcode com.zrk.leetcode.MaximumLengthOfPairChain
 * @description
 * @updateVersion 1.0
 * <p>
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * <p>
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * <p>
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * <p>
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 * @updateTime 2018/1/5 11:15
 */

public class Solution {

    public static void main(String[] args) {
        int[][] pairs= {{1,2},{2,3},{3,4}};
        System.out.println("result = "+new Solution().findLongestChain(pairs));
    }

    public int findLongestChain(int[][] pairs) {
        if (pairs == null) return 0;
        if (pairs.length == 1)
            return 1;
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] lastPair = null;
        int count = 1;
        for (int i = 0; i < pairs.length; i++) {
            int[] pair = pairs[i];
            if (lastPair == null) {
                lastPair = pair;
            } else {
                if (pair[0] > lastPair[1]) {
                    lastPair = pair;
                    count++;
                } else {
                    if (pair[1] < lastPair[1]) {
                        lastPair = pair;
                    }
                }
            }
        }
        return count;
    }
}
