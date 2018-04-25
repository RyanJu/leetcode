package com.zrk.leetcode.easy.RemoveElement;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/8 15:33 1.0
 * @time 2018/4/8 15:33
 * @project leetcode com.zrk.leetcode.easy.RemoveElement
 * @description Given an array and a value, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example:
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * @updateVersion 1.0
 * @updateTime 2018/4/8 15:33
 */

public class Solution {

    public static void main(String[] args){
        int[] arr = {3,2,2,3,1,2,9,10,3,3,33,13,2};
        int l = new Solution().removeElement(arr,3);
        System.out.println(l+" ; "+Arrays.toString(arr));
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;

        int moveIndex = 0;
        for (int i = 0, size = nums.length; i < size; i++) {
            if (nums[i] != val) {
                nums[moveIndex++] = nums[i];
            }
        }
        return moveIndex;
    }
}
