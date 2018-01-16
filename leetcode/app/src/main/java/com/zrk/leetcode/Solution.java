package com.zrk.leetcode;
/**
 * Created by zhurongkun on 2017/9/14.
 */

import java.util.Arrays;

/**
 * @author zhurongkun
 * @version 1.0
 * @date 2017/9/14 16:46
 * @package com.zrk.leetcode
 * @project leetcode
 * @description
 * @update
 */
public class Solution {
    public static void main(String[] args) {
        int[] prices = new int[1000000];
        for (int i = 0; i < 10001; i++) {
            prices[i] = 10000 - i;
        }
        Solution solution = new Solution();
        int max = solution.maxProfit(prices);
        System.out.println("max=" + max);
//        System.out.println("shrink:"+ Arrays.toString(solution.shrink(prices)));
    }



    public int maxProfit(int[] prices) {
        prices = shrink(prices);

        int n = prices.length;
        if (n <= 1) return 0;

        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                temp[i][j] = prices[j] - prices[i];
            }
        }
        int[] first = new int[n];
        first[0] = 0;
        for (int i = 1; i < n; i++) {
            int max = first[i - 1];
            for (int j = 0; j < i; j++) {
                if (max < temp[j][i]) {
                    max = temp[j][i];
                }
            }
            first[i] = max;
        }

        int[] last = new int[n];
        last[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int max = last[i + 1];
            for (int j = n - 1; j > i; j--) {
                if (max < temp[i][j])
                    max = temp[i][j];
            }
            last[i] = max;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int t = 0;
            if (i < n - 1) {
                t = first[i] + last[i + 1];
            } else {
                t = first[i];
            }
            max = max < t ? t : max;
        }
        return max;
    }

    private int[] shrink(int[] prices) {
        if (prices.length <= 1) return prices;
        int[] temp = new int[prices.length];
        int index = 0;
        temp[index++] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1]) continue;
            temp[index++] = prices[i];
        }
        return Arrays.copyOf(temp, index);
    }
}
