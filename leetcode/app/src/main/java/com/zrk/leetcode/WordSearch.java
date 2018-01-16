package com.zrk.leetcode;

import android.support.annotation.DimenRes;
import android.support.annotation.IntRange;
import android.text.TextUtils;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhurongkun on 2017/9/18.
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word == null || board == null || board.length < 1) return false;
        int lenX = board[0].length;
        int lenY = board.length;

        for (int x = 0; x < lenX; x++) {
            for (int y = 0; y < lenY; y++) {
                if (search(board, x, y, lenX, lenY, word, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean search(char[][] board, int x, int y, int lenX, int lenY, String word, int i) {
        if (x < 0 || x >= lenX || y < 0 || y >= lenY || i >= word.length()) return false;
        char c1 = board[y][x];
        char c2 = word.charAt(i);
        if (c1 == c2) {
            if (i == word.length() - 1) return true;
            board[y][x] = 0;
            if (search(board, x + 1, y, lenX, lenY, word, i + 1)) return true;
            if (search(board, x, y + 1, lenX, lenY, word, i + 1)) return true;
            if (search(board, x - 1, y, lenX, lenY, word, i + 1)) return true;
            if (search(board, x, y - 1, lenX, lenY, word, i + 1)) return true;
            board[y][x] = c1;
        }
        return false;
    }

    private boolean search(char[][] board, int curX, int curY, int lenX, int lenY, String word, int wordCharIndex, int next, HashSet<Long> mark) {
        if (curX >= lenX || curX < 0 || curY >= lenY || curY < 0 || next > 3 || isPointMarked(mark, curX, curY))
            return false;

        char curBoardChar = board[curY][curX];
        char curWordChar = word.charAt(wordCharIndex);
        if (curBoardChar == curWordChar) {
            if (wordCharIndex >= word.length() - 1) return true;
            int nextX = curX;
            int nextY = curY;
            markPoint(curX, curY, mark);
            //search next
            if (next == 0) nextX++;
            if (next == 1) nextY++;
            if (next == 2) nextX--;
            if (next == 3) nextY--;
            if (search(board, nextX, nextY, lenX, lenY, word, wordCharIndex + 1, 0, mark))
                return true;
            unmarkPoint(curX, curY, mark);
            return search(board, curX, curY, lenX, lenY, word, wordCharIndex, next + 1, mark);
        }
        return false;
    }

    private void unmarkPoint(int curX, int curY, HashSet<Long> mark) {
        long marked = makeMark(curX, curY);
        mark.remove(marked);
    }

    private void markPoint(int curX, int curY, HashSet<Long> mark) {
        long marked = makeMark(curX, curY);
        mark.add(marked);
    }

    private long makeMark(int x, int y) {
        return ((long) x) << 32 | ((long) y);
    }

    private boolean isPointMarked(HashSet<Long> mark, int x, int y) {
        long marked = makeMark(x, y);
        return mark.contains(marked);
    }

}
