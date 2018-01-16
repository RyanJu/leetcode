package com.zrk.leetcode.medium.ContainerWithMostWater;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/12 16:27 1.0
 * @time 2018/1/12 16:27
 * @project leetcode com.zrk.leetcode.medium.ContainerWithMostWater
 * @description Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * y ^
 * |
 * |     a2
 * |     |  a3          an
 * |  a1 |  |     a5    |
 * |  |  |  |  a4 |     |
 * |  |  |  |  |  | ..  |
 * --------------------------->
 * 0  1  2  3  4  5 ..  n     x
 * @updateVersion 1.0
 * @updateTime 2018/1/12 16:27
 */

public class Solution {

    public static void main(String[] args) {
        int[] height = new int[15000];
        for (int i = 0; i < height.length; i++) {
            height[i] = (int) (Math.random()*height.length+1);
        }
        int maxArea = new Solution().maxArea(height);
        System.out.println(maxArea);
    }

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int result = 0;
        while (i < j) {
            int area;
            if (height[i] <= height[j]) {
                area = height[i] * (j - i);
                result = result < area ? area : result;
                i++;
            } else {
                area = height[j] * (j - i);
                result = result < area ? area : result;
                j--;
            }
        }
        return result;
    }

}
