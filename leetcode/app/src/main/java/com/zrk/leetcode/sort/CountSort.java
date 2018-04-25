package com.zrk.leetcode.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/2/23 15:50 1.0
 * @time 2018/2/23 15:50
 * @project leetcode com.zrk.leetcode.sort
 * @description 带排序的数组中所有数不大于k(k>0)
 * @updateVersion 1.0
 * @updateTime 2018/2/23 15:50
 */

public class CountSort {

    public static void main(String[] args) {
        int[] test = new int[10];

        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt(20);
        }

        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(new CountSort().sort(test)));
    }


    public int[] sort(int[] array) {
        if (array == null)
            return array;
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            max = max < array[i] ? array[i] : max;
        }

        return _sort(array, max + 1);

    }

    int[] _sort(int[] array, int k) {
        if (array.length <= 1)
            return array;

        int[] temp = new int[k];
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            temp[array[i]]++;
        }

        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }

        for (int i = 0; i < array.length; i++) {
            result[temp[array[i]] - 1] = array[i];
            temp[array[i]]--;
        }

        return result;
    }
}
