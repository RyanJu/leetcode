package com.zrk.leetcode.medium.SearchInRotatedSortedArray;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/27 17:51 1.0
 * @time 2018/4/27 17:51
 * @project leetcode com.zrk.leetcode.medium.SearchInRotatedSortedArray
 * @description Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * @updateVersion 1.0
 * @updateTime 2018/4/27 17:51
 */

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        int medium;
        while (l <= r) {
            medium = (l + r) / 2;
            if (nums[l] == target) {
                return l;
            }
            if (nums[r] == target) {
                return r;
            }
            if (nums[medium] == target) {
                return medium;
            }

            if (inRange(nums, l, medium, target)) {
                l = l + 1;
                r = medium - 1;
            } else if (inRange(nums, medium, r, target)) {
                l = medium + 1;
                r = r - 1;
            } else {
                return -1;
            }
        }
        return -1;
    }

    private boolean inRange(int[] nums, int l, int r, int target) {
        if (nums[l] > nums[r]) {
            return nums[l] <= target || nums[r] >= target;
        }
        return nums[l] <= target && target <= nums[r];
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 1;
        int search = new Solution().search(nums, target);
        System.out.println("search " + search);
    }

    public static void f() throws IllegalStateException {
        throw new IllegalStateException();
    }

    public static void g() throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }

    public static int[] t() {
        int[] i = {0};
        try {
            i[0] = 100;
            System.out.println(i[0]);
            return i;
        } catch (Exception e) {
            return i;
        } finally {
            i[0] = 200;
        }
    }
}