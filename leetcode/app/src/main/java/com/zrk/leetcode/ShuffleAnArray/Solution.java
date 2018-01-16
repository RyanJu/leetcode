package com.zrk.leetcode.ShuffleAnArray;

import java.util.Arrays;
import java.util.Random;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/5 9:57 1.0
 * @time 2018/1/5 9:57
 * @project leetcode com.zrk.leetcode.ShuffleAnArray
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/5 9:57
 */

public class Solution {
    private int[] mArray;

    public Solution(int[] nums) {
        this.mArray = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return mArray;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        if (mArray == null || mArray.length <= 1) {
            return mArray;
        }

        int length = mArray.length;
        int r1, r2;
        Random random = new Random();
        int[] result = Arrays.copyOf(mArray, length);
        for (int i = 0; i < length / 2 + 1; i++) {
            r1 = random.nextInt(length);
            r2 = random.nextInt(length);

            //swap result[r1] result[r2]
            if (r1 != r2) {
                int temp = result[r1];
                result[r1] = result[r2];
                result[r2] = temp;
            }
        }
        return result;
    }
}
