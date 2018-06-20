package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/19 16:58 1.0
 * @time 2018/6/19 16:58
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/19 16:58
 */

public class RandomSelect {


    public static void main(String[] args) {
        int[] array = {6, 3, 1, 64, 2, 23, 111, 3, 4};
        RandomSelect randomSelect = new RandomSelect();
        int kth = randomSelect.select(array, 6);
        System.out.println("kth = " + kth);
    }

    /**
     * 求第k小的元素
     *
     * @param array
     * @param k
     * @return
     */
    public int select(int[] array, int k) {
        return quickSelect(array, 0, array.length, k);
    }

    /**
     * @param array
     * @param start inclusive
     * @param end   exclusive
     * @param k
     * @return
     */
    private int quickSelect(int[] array, int start, int end, int k) {
        if (start >= end) {
            return array[start];
        }

        int q = partition(array, start, end);
        int len = q - start + 1;
        if (len == k) {
            return array[q];
        } else if (k < len) {
            return quickSelect(array, start, q , k);
        } else {
            return quickSelect(array, q + 1, end, k - len);
        }
    }

    private int partition(int[] array, int start, int end) {
        int pivot = start;
        int x = array[pivot];
        end--;
        while (start <= end) {
            while (start <= end && array[end] > x) end--;
            if (start <= end) {
                array[pivot] = array[end];
                pivot = end;
            }
            end--;

            while (start <= end && array[start] <= x) start++;
            if (start <= end) {
                array[pivot] = array[start];
                pivot = start;
            }
            start++;
        }
        array[pivot] = x;
        return pivot;
    }
}
