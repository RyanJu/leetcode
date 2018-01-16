package com.zrk.leetcode;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by zhurongkun on 2017/9/15.
 */

public class CountRangeSum {
    public static void main(String[] args){
        CountRangeSum countRangeSum = new CountRangeSum();
        int[] nums = {-2, 5, -1};
        System.out.println(countRangeSum.countRangeSum(nums,-2,2));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) return 0;
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int rangeSum = j > i ? sum[j] - sum[i] : nums[i];
                if (inRange(rangeSum, lower, upper)) count++;
            }
        }
        return count;
    }

    private boolean inRange(int num, int lower, int upper) {
        return num <= upper && num >= lower;
    }
}
