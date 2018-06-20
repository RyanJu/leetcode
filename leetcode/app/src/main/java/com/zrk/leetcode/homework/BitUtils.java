package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/20 14:01 1.0
 * @time 2018/6/20 14:01
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/20 14:01
 */

public class BitUtils {
    /**
     * set {@code pos} bit to 1, pos is counting from the lowest bit(right)
     *
     * @param t
     * @param pos
     * @param t
     * @return
     */
    public static int bitSet(int t, int pos) {
        return t | (1 << (pos % Integer.SIZE));
    }

    public static int bitClear(int t, int pos) {
        return t & (~(1 << (pos % Integer.SIZE)));
    }

    public static int bitGet(int t, int pos) {
        return ((t >> (pos % Integer.SIZE)) & 1);
    }

    public static void main(String[] a) {
        int number = 127;
        int value;
        System.out.println(Integer.toBinaryString(number));
        System.out.println(Integer.toBinaryString(value = BitUtils.bitSet(number, 12)) + " , " + value);
        System.out.println(Integer.toBinaryString(value = BitUtils.bitSet(number, 1298)) + " , " + value);
        System.out.println(Integer.toBinaryString(value = BitUtils.bitSet(number, 31)) + " , " + value);
    }

    private BitUtils(){}

}
