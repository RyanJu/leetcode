package com.zrk.leetcode.medium.NextPermutation;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/25 16:32 1.0
 * @time 2018/4/25 16:32
 * @project leetcode com.zrk.leetcode.medium.NextPermutation
 * @description Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * @updateVersion 1.0
 * @updateTime 2018/4/25 16:32
 */

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = nums.length - 1, j = 0; i > 0 && j < nums.length && j <= i; i--, j++) {
            if (nums[i] > nums[i - 1]) {
                int t = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = t;
                break;
            }
            if (nums[j] < nums[j + 1]) {
                int t = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = t;
                break;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    public static void main(String[] args) {
        int nums[] = {1, 3,2};
        System.out.println(Arrays.toString(nums));
        Solution solution = new Solution();
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}