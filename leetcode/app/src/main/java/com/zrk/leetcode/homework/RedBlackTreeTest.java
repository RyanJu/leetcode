package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/15 11:30 1.0
 * @time 2018/6/15 11:30
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/15 11:30
 */

public class RedBlackTreeTest {
    public static void main(String[] a) {
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        int[] arr = {11, 2, 14, 1, 7, 15, 5, 8};
        for (int i = 0; i < arr.length; i++) {
            rbTree.insert(arr[i]);
            rbTree.printTree();
        }

        rbTree.insert(4);
        rbTree.printTree();
    }
}
