package com.zrk.leetcode.easy.TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/8 10:51 1.0
 * @time 2018/1/8 10:51
 * @project leetcode com.zrk.leetcode.easy.TwoSum
 * @description Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @updateVersion 1.0
 * @updateTime 2018/1/8 10:51
 */

public class Solution {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] ints = new Solution().twoSum(nums, 6);
        System.out.println("" + Arrays.toString(ints));
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2)
            return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int m = target - n;
            if (map.containsKey(m)) {
                return new int[]{i, map.get(m)};
            }
            if (!map.containsKey(n)) {
                map.put(n, i);
            }
        }
        return null;
    }
}
