package com.zrk.leetcode.hard.MedianOfTwoSortedArrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/8 15:22 1.0
 * @time 2018/1/8 15:22
 * @project leetcode com.zrk.leetcode.hard.MedianOfTwoSortedArrays
 * @description There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 * @updateVersion 1.0
 * @updateTime 2018/1/8 15:22
 */

public class Solution {

    public static void main(String[] args) {
        /**
         * [4,6,8,10]
         [1,2,3,5,7,9]
         */
        int[] nums1 = {4};
        int[] nums2 = {6};
        double median = new Solution().findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int k1 = (n + m) / 2;
        int k2 = (n + m - 1) / 2;
        if (k1 == k2) {
            return findKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k1);
        } else {
            return (findKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k1) + findKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k2)) / 2.0;
        }
    }

    private double findKth(int[] A, int aLeft, int aRight, int[] B, int bLeft, int bRight, int k) {


        if (aRight - aLeft > bRight - bLeft) {
            return findKth(B, bLeft, bRight, A, aLeft, aRight, k);
        }
        if (aRight < aLeft) {
            return B[k];
        }

        if (k == 0) {
            return Math.min(A[aLeft], B[bLeft]);
        }


        if (aRight - aLeft == 0 && bLeft + k <= bRight) {
            if (A[aLeft] < B[bLeft + k - 1]) {
                return B[bLeft + k - 1];
            } else if (A[aLeft] > B[bLeft + k]) {
                return B[bLeft + k];
            } else {
                return A[aLeft];
            }
        }


        int ka = Math.min(aRight - aLeft, k / 2);
        int kb = (k + 1) - (ka + 1) - 1;

        if (A[aLeft + ka] < B[bLeft + kb]) {
            return findKth(A, aLeft + ka + 1, aRight, B, bLeft, bRight, k - ka - 1);
        } else if (A[aLeft + ka] > B[bLeft + kb]) {
            return findKth(A, aLeft, aRight, B, bLeft + kb + 1, bRight, k - kb - 1);
        } else {
            return A[aLeft + ka];
        }
    }


}
