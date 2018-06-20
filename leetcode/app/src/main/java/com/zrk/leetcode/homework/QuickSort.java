package com.zrk.leetcode.homework;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/19 15:25 1.0
 * @time 2018/6/19 15:25
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/19 15:25
 */

public class QuickSort {
    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int[] array = {13,12,11,10,9, 8, 7, 6, 5, 4, 3, 2, 1};
        new QuickSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array) {
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, array.length);
    }

    private void quickSort(int[] array, int start, int end) {
        int p = partition(array, start, end);
        if (start < p) {
            quickSort(array, start, p);
        }
        if (p < end) {
            quickSort(array, p + 1, end);
        }
    }

    private int partition(int[] array, int start, int end) {
        if (start >= end) {
            return start;
        }

        int randomPivot = chooseRandomPivot(start, end);

        System.out.println("from [" + start + "," + end + ")" + " choose " + randomPivot);

        int temp = array[start];
        array[start] = array[randomPivot];
        array[randomPivot] = temp;

        int pivot = start;
        int x = array[pivot];
        end--;
        while (start <= end) {
            while (start <= end && array[end] >= x) end--;
            if (start <= end) {
                array[pivot] = array[end];
                pivot = end;

            }
            end--;
            while (start <= end && array[start] < x) start++;
            if (start <= end) {
                array[pivot] = array[start];
                pivot = start;
            }
            start++;
        }
        array[pivot] = x;
        return pivot;
    }

    private int chooseRandomPivot(int start, int end) {
        int r1 = (int) (random.nextFloat() * (end - start) + start);
        int r2 = (int) (random.nextFloat() * (end - start) + start);
        int r3 = (int) (random.nextFloat() * (end - start) + start);
        if (r1 < r2) {
            if (r2 < r3) {
                return r2;
            } else {
                if (r1 < r3)
                    return r3;
                else
                    return r1;
            }
        } else {
            if (r1 < r3) {
                return r1;
            } else {
                if (r2 < r3) {
                    return r3;
                } else {
                    return r2;
                }
            }
        }
    }


}
