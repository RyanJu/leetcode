package com.zrk.leetcode;

/**
 * Created by zhurongkun on 2017/9/15.
 * Best Time to Buy and Sell Stock
 */

public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit maxProfit=new MaxProfit();
        int[] prices={7, 6, 4, 3, 1};
        int i = maxProfit.maxProfit(prices);
        System.out.println(i);
    }

    public int maxProfit(int[] prices) {
        if (prices == null) return 0;
        if (prices.length <= 1) return 0;
        int minBuy = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minBuy = Math.min(minBuy, prices[i]);
            int profit = prices[i] - minBuy;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }
}
