package com.zrk.leetcode.medium._3SumClosest;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/15 16:57 1.0
 * @time 2018/1/15 16:57
 * @project leetcode com.zrk.leetcode.medium._3SumClosest
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/15 16:57
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,1,55};
        int i = new Solution().threeSumClosest(nums, 3);
        System.out.println(i);
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        if (nums.length <= 3) {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            int leftTarget = target - nums[i];
            while (start < end) {
                int cur = nums[start] + nums[end];
                if (cur == leftTarget) {
                    return target;
                }
                int t = nums[i] + nums[start] + nums[end];
                if (min > Math.abs(target - t)) {
                    min = Math.abs(target - t);
                    sum = t;
                }
                if (cur > leftTarget) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return sum;
    }
}
