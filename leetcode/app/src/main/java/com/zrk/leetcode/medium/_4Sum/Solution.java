package com.zrk.leetcode.medium._4Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/16 10:34 1.0
 * @time 2018/1/16 10:34
 * @project leetcode com.zrk.leetcode.medium._4Sum
 * @description Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * @updateVersion 1.0
 * @updateTime 2018/1/16 10:34
 */

public class Solution {

    public static void main(String[] args) {
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        List<List<Integer>> lists = new Solution().fourSum(nums, 0);
        for (List<Integer> item : lists) {
            for (int i : item) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4)
            return result;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {

                int b = nums[j];

                int innerL = j + 1;
                int innerR = nums.length - 1;
                while (innerL < innerR) {
                    int c = nums[innerL];
                    int d = nums[innerR];
                    if (a + b + c + d == target) {
                        List<Integer> item = new ArrayList<>();
                        item.add(a);
                        item.add(b);
                        item.add(c);
                        item.add(d);
                        result.add(item);

                        while (innerL + 1 < innerR && nums[innerL + 1] == c) {
                            innerL++;
                        }

                        while (innerR - 1 > innerL && nums[innerR - 1] == d) {
                            innerR--;
                        }
                        innerL++;
                        innerR--;
                    } else if (a + b + c + d < target) {
                        innerL++;
                    } else {
                        innerR--;
                    }
                }

                while (j + 1 < nums.length && b == nums[j + 1]) j++;
            }

            while (i + 1 < nums.length && a == nums[i + 1]) i++;
        }
        return result;
    }
}
