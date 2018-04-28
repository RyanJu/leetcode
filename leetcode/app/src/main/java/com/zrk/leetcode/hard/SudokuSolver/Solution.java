package com.zrk.leetcode.hard.SudokuSolver;

import android.support.annotation.IntRange;

import java.util.Arrays;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/28 14:12 1.0
 * @time 2018/4/28 14:12
 * @project leetcode com.zrk.leetcode.hard.SudokuSolver
 * @description Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * <p>
 * <p>
 * A sudoku puzzle...
 * <p>
 * <p>
 * ...and its solution numbers marked in red.
 * <p>
 * Note:
 * <p>
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 * @updateVersion 1.0
 * @updateTime 2018/4/28 14:12
 */

class Solution {
    static final int SUDOKU_SIZE = 9;


    static class Box {
        boolean[] selected = new boolean[SUDOKU_SIZE + 1];
    }

    static class Item {
        boolean[] alternative = new boolean[SUDOKU_SIZE];
        int alterCount = SUDOKU_SIZE;
        int i, j;
        int current;

        public Item(int i, int j, int current) {
            this.i = i;
            this.j = j;
            this.current = current;
            Arrays.fill(alternative, true);
        }
    }

    public void solveSudoku(char[][] board) {
        Box[] rows = new Box[SUDOKU_SIZE];
        Box[] columns = new Box[SUDOKU_SIZE];
        Box[] boxes = new Box[SUDOKU_SIZE];
        Item[][] boardItems = new Item[SUDOKU_SIZE][SUDOKU_SIZE];
        initBoxes(board, rows, columns, boxes, boardItems);

        updateBoardItems(board, rows, columns, boxes, boardItems);


        printBox(rows);
        printBox(columns);
        printBox(boxes);

    }

    private void initBoxes(char[][] board, Box[] rows, Box[] columns, Box[] boxes, Item[][] boardItems) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                char c = board[i][j];

                if (rows[i] == null) {
                    rows[i] = new Box();
                }
                rows[i].selected[charToIndex(c)] = true;

                if (columns[j] == null) {
                    columns[j] = new Box();
                }
                columns[j].selected[charToIndex(c)] = true;

                int boxId = ijToBoxId(i, j);
                if (boxes[boxId] == null) {
                    boxes[boxId] = new Box();
                }
                boxes[boxId].selected[charToIndex(c)] = true;

                if (c == '.') {
                    boardItems[i][j] = new Item(i, j, -1);
                } else {
                    boardItems[i][j] = new Item(i, j, c - '0');
                }
            }
        }
    }

    private void updateBoardItems(char[][] board, Box[] rows, Box[] columns, Box[] boxes, Item[][] boardItems) {
        Item item;
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                item = boardItems[i][j];
                if (item.current == -1) {
                    //empty item
                    int rowId = i;
                    int colId = j;
                    int boxId = ijToBoxId(i,j);
                }
            }
        }
    }

    private void printBox(Box[] boxes) {
        System.out.println("box begin");
        for (int i = 0; i < boxes.length; i++) {
            System.out.print("line " + i + " : ");
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                if (boxes[i].selected[j]) {
                    System.out.print((j + 1) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("box end");
    }

    private int ijToBoxId(int i, int j) {
        return i / 3 * 3 + j / 3;
    }

    private int charToIndex(char c) {
        if (c == '.') {
            return SUDOKU_SIZE;
        }
        return c - '1';
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solution.solveSudoku(board);
    }
}
