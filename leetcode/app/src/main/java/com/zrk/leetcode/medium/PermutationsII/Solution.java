package com.zrk.leetcode.medium.PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/26 17:20 1.0
 * @time 2018/4/26 17:20
 * @project leetcode com.zrk.leetcode.medium.PermutationsII
 * @description Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * @updateVersion 1.0
 * @updateTime 2018/4/26 17:20
 */

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        helper(result, new boolean[nums.length], new ArrayList<Integer>(), nums);
        return result;
    }

    private void helper(List<List<Integer>> result, boolean[] set, List<Integer> onePermutation, int[] nums) {
        if (onePermutation.size() >= nums.length) {
            result.add(new ArrayList<Integer>(onePermutation));
            return;
        }
        for (int i = 0; i < set.length; i++) {
            if (!set[i]) {
                //not selected number
                /**
                 * eg:
                 * 1,1,2,2
                 * now i=1,  and nums[i]==nums[i-1]
                 * if nums[i-1] is not added yet,suggest that this is a duplicate permutation
                 */
                if (i > 0 && nums[i] == nums[i - 1] && !set[i - 1]) {
                    continue;
                }
                set[i] = true;
                onePermutation.add(nums[i]);
                helper(result, set, onePermutation, nums);
                set[i] = false;
                onePermutation.remove(onePermutation.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1};
        List<List<Integer>> permute = new Solution().permuteUnique(nums);
        System.out.println(permute);
    }
}
