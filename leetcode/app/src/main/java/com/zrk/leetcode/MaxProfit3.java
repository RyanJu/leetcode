package com.zrk.leetcode;

/**
 * Created by zhurongkun on 2017/9/15.
 * Best Time to Buy and Sell Stock III
 */

public class MaxProfit3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {10, 9, 8, 7, 1, 2, 3, 2, 11};
        int max = solution.maxProfit(new int[]{});
        System.out.println("max=" + max);
    }

    public static class Solution {

        public int maxProfit(int[] prices) {
            if (prices == null) return 0;
            if (prices.length <= 1) return 0;
            int len = prices.length;
            int minBuy = prices[0];
            int maxSell = prices[len - 1];

            //travel prices to get max profit from 0 to i,save the max profit to array firstTransaction[i]
            int[] firstTransaction = new int[len];
            firstTransaction[0] = 0;
            for (int i = 1; i < len; i++) {
                //update the min price
                minBuy = Math.min(minBuy, prices[i]);
                //current profit = current price-minBuy
                int profit = prices[i] - minBuy;
                //max profit of [0,i] = max between former profit [0,i-1] and current
                firstTransaction[i] = Math.max(firstTransaction[i - 1], profit);
            }

            //travel prices from end to 0 ,get the 2nd max profit
            int[] secondTransaction = new int[len];
            secondTransaction[len - 1] = 0;
            for (int i = len - 2; i >= 0; i--) {
                //update max sell
                maxSell = Math.max(maxSell, prices[i]);
                //current profit
                int profit = maxSell - prices[i];
                // current max profit [i,end] = max between current profit and latter
                secondTransaction[i] = Math.max(secondTransaction[i + 1], profit);
            }

            // max profit of 2 transaction is the sum of firstTransaction[i] and secondTransaction[i]
            int maxProfit = 0;
            for (int i = 0; i < len; i++) {
                maxProfit = Math.max(maxProfit, firstTransaction[i] + secondTransaction[i]);
            }
            return maxProfit;
        }
    }
}
