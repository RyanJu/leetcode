package com.zrk.leetcode.homework;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/19 16:41 1.0
 * @time 2018/6/19 16:41
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/19 16:41
 */

public class CountingSort {

    public static void main(String[] a) {
        CountingSort countingSort = new CountingSort();
        int[] array = {3, 6, 1, 0, 2, 9,};
        System.out.println(Arrays.toString(array));
        array = countingSort.sort(array, 10);
        System.out.println(Arrays.toString(array));
    }

    /**
     * @param array
     * @param k     the max element of array
     */
    public int[] sort(int[] array, int k) {
        int c[] = new int[k + 1];
        for (int i = 0; i < array.length; i++) {
            c[array[i]]++;
        }
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }

        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[c[array[i]] - 1] = array[i];
            c[array[i]]--;
        }
        return result;
    }
}
