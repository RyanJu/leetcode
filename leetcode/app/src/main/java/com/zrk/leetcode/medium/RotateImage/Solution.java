package com.zrk.leetcode.medium.RotateImage;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/7 16:50 1.0
 * @time 2018/6/7 16:50
 * @project leetcode com.zrk.leetcode.medium.RotateImage
 * @description You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * Example 2:
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * @updateVersion 1.0
 * @updateTime 2018/6/7 16:50
 */

public class Solution {
    public void rotate(int[][] matrix) {
        int i = 0, j = 0;
        int n = matrix.length - 1;
        int fi = n;
        int fj = n;

        while (i < fi && j < fj) {
            int m = j;
            for (; m < fj; m++) {
                int cur_i = i;
                int cur_j = m;
                for (int k = 0; k < 3; k++) {
                    int next_i = n - cur_j;
                    int next_j = cur_i;
                    int temp = matrix[cur_i][cur_j];
                    matrix[cur_i][cur_j] = matrix[next_i][next_j];
                    matrix[next_i][next_j] = temp;
                    cur_i = next_i;
                    cur_j = next_j;
                }
            }
            i++;
            fi--;
            j++;
            fj--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11, 3},
                {2, 4, 8, 10, 1},
                {13, 3, 6, 7, 7},
                {15, 14, 12, 16, 10},
                {3, 4, 21, 22, 23},
        };

        System.out.println("before");
        printMatrix(matrix);

        new Solution().rotate(matrix);

        System.out.println("after");
        printMatrix(matrix);

    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%8d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
