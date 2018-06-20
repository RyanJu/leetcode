package com.zrk.leetcode.easy.MaximumSubarray;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/8 9:39 1.0
 * @time 2018/6/8 9:39
 * @project leetcode com.zrk.leetcode.easy.MaximumSubarray
 * @description Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * @updateVersion 1.0
 * @updateTime 2018/6/8 9:39
 */

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE;
        int max_i = 0, max_j = 0;

        int cur = 0;
        int cur_i = 0;
        int cur_j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (cur <= 0) {
                cur = nums[i];
                cur_i = i;
            } else {
                cur += nums[i];
            }

            cur_j = i;


            if (max < cur) {
                max = cur;
                max_i = cur_i;
                max_j = cur_j;
            }
        }

        return max;
    }

    public int maxSubArray2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = nums[0];
        for (int i = 0, cur = 0; i < nums.length; i++) {
            if (cur < 0)
                cur = nums[i];
            else
                cur += nums[i];
            if (max < cur)
                max = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1,};

        int max = new Solution().maxSubArray(nums);

    }
}
