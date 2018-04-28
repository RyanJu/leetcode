package com.zrk.leetcode.medium.Permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/26 16:42 1.0
 * @time 2018/4/26 16:42
 * @project leetcode com.zrk.leetcode.medium.Permutations
 * @description Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * @updateVersion 1.0
 * @updateTime 2018/4/26 16:42
 */

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        //backtrace
        helper(result, new boolean[nums.length], new ArrayList<Integer>(), nums);
        return result;
    }

    /**
     * @param result
     * @param set eg: set[true,true,false] means num[0],num[1] has been added to this permutation already,and num[2] not.
     * @param onePermutation current permutation
     * @param nums
     */
    private void helper(List<List<Integer>> result, boolean[] set, List<Integer> onePermutation, int[] nums) {
        if (onePermutation.size() >= nums.length) {
            //a permutation complete
            result.add(new ArrayList<Integer>(onePermutation));
            return;
        }
        //find first number in nums[] that has not been added to this permutation
        for (int i = 0; i < set.length; i++) {
            if (!set[i]) {
                //not selected number found!
                set[i] = true;
                onePermutation.add(nums[i]);

                //trace further
                helper(result, set, onePermutation, nums);

                //trace back
                set[i] = false;
                onePermutation.remove((Integer) nums[i]);
            }
        }
    }

    public static void main(String[] a) {
        int[] nums = {1, 2, 3,4};
        List<List<Integer>> permute = new Solution().permute(nums);
        System.out.println(permute);
    }
}
