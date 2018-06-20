package com.zrk.leetcode.homework;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/6 14:17 1.0
 * @time 2018/6/6 14:17
 * @project leetcode com.zrk.leetcode.homework
 * @description Description
 * 矩阵链乘法，动态规划
 * <p>
 * 给定n个矩阵｛A1,A2,…,An｝，其中Ai与Ai+1是可乘的，i=1,2 ,…,n-1。如何确定计算矩阵连乘积的计算次序，使得依此次序计算矩阵连乘积需要的数乘次数最少。
 * <p>
 * Input
 * <p>
 * 有N个矩阵连乘,用一行有n+1个数数组表示,表示是n个矩阵的行及第n个矩阵的列,它们之间用空格隔开.
 * <p>
 * Output
 * <p>
 * 你的输出应该有C行，即每组测试数据的输出占一行，它是计算出的矩阵最少连乘积次数，输出最优全括号结构
 * <p>
 * Sample Input
 * <p>
 * <p>
 * 10 100 5 50
 * <p>
 * <p>
 * <p>
 * <p>
 * Sample Output
 * <p>
 * <p>
 * <p>
 * <p>
 * 7500
 * ((A1A2)A3)
 * @updateVersion 1.0
 * @updateTime 2018/6/6 14:17
 */

public class MatrixChainMultiply {
    /**
     * @param p [0,1,...n+1],共有矩阵A[n]，A[1]是p[0] x p[1]...A[i]是p[i-1] x p[i]
     * @return 最小需要的乘法数
     */
    public int solution(int[] p) {
        if (p == null || p.length <= 1) {
            return 0;
        }
        int n = p.length - 1;

        //m[i][j]表示矩阵A[i]..A[j]的最小乘法数
        int[][] m = new int[n + 1][n + 1];
        return recurse(p, m, 1, n);
    }

    private int recurse(int[] p, int[][] m, int i, int j) {
        if (i >= j)
            return 0;
        if (m[i][j] != 0) {
            return m[i][j];
        }
        if (i + 1 == j) {
            return m[i][j] = p[i - 1] * p[i] * p[j];
        }
        int min_ij = Integer.MAX_VALUE;
        for (int k = i + 1; k <= j; k++) {
            int m_ik = recurse(p, m, i, k);
            int m_kj = recurse(p, m, k + 1, j);
            int temp = m_ik + m_kj + p[i - 1] * p[k] * p[j];
            if (min_ij > temp) {
                min_ij = temp;
                m[i][j] = temp;
            }
        }
        return min_ij;
    }

    public static void main(String[] args) {
        int[] p = {5, 10, 3, 12, 5};
        System.out.println(new MatrixChainMultiply().solution(p));
    }
}
