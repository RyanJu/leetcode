package com.zrk.leetcode.medium.SearchForRange;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/28 13:53 1.0
 * @time 2018/4/28 13:53
 * @project leetcode com.zrk.leetcode.medium.SearchForRange
 * @description Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * @updateVersion 1.0
 * @updateTime 2018/4/28 13:53
 */

class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length < 1) {
            return result;
        }

        int i = binarySearch(nums, target);
        System.out.println("binarySearch " + i);
        if (i == -1) {
            return result;
        } else {
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && nums[l] == target) l--;
            while (r < nums.length && nums[r] == target) r++;
            result[0] = l + 1;
            result[1] = r - 1;
            return result;
        }
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l <= r) {
            if (nums[l] == target) {
                return l;
            }
            if (nums[r] == target) {
                return r;
            }
            m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l] > target) {
                return -1;
            } else if (nums[r] < target) {
                return -1;
            } else if (nums[m] > target) {
                l++;
                r = m - 1;
            } else {
                r--;
                l = m + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(solution.searchRange(nums, 8)));
    }
}
