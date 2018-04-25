package com.zrk.leetcode.easy.RemoveDuplicatesfromSortedArray;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/19 18:11 1.0
 * @time 2018/1/19 18:11
 * @project leetcode com.zrk.leetcode.easy.RemoveDuplicatesfromSortedArray
 * @description Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example:
 * <p>
 * Given nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 * @updateVersion 1.0
 * @updateTime 2018/1/19 18:11
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}
