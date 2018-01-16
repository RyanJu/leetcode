package com.zrk.leetcode;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhurongkun on 2017/9/19.
 * <p>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * <p>
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

public class WordSearch2 {
    public List<String> findWords2(char[][] board, String[] words) {
        if (board == null || board.length < 1 || words == null || words.length < 1)
            return new ArrayList<>();
        TrieNode root = createTrie(words);
        int lenX = board[0].length;
        int lenY = board.length;
        Set<String> result = new HashSet<>();
        for (int y = 0; y < lenY; y++) {
            for (int x = 0; x < lenX; x++) {
                search(board, x, y, lenX, lenY, root, new StringBuilder(), result);
            }
        }
        return new ArrayList<>(result);
    }


    private void search(char[][] board, int x, int y, int lenX, int lenY, TrieNode root, StringBuilder stringBuilder, Set<String> result) {
        if (x < 0 || x >= lenX || y < 0 || y >= lenY) return;
        char c1 = board[y][x];
        if (c1 == 0) return;
        if (root.childs == null) return;
        int position = c1 - 'a';
        TrieNode node = root.childs[position];

        if (node != null) {//exist prefix
            node.exists = true;
            stringBuilder.append(c1);
            if (node.isWord)
                result.add(stringBuilder.toString());
            board[y][x] = 0;
            int i = stringBuilder.length() - 1;
            search(board, x + 1, y, lenX, lenY, node, stringBuilder, result);
            search(board, x, y + 1, lenX, lenY, node, stringBuilder, result);
            search(board, x - 1, y, lenX, lenY, node, stringBuilder, result);
            search(board, x, y - 1, lenX, lenY, node, stringBuilder, result);
            board[y][x] = c1;
            stringBuilder.delete(i, stringBuilder.length());
        }
    }

//    public List<String> findWords(char[][] board, String[] words) {
//        if (board == null || board.length < 1 || words == null || words.length < 1)
//            return new ArrayList<>();
//        TrieNode root = createTrie(words);
//        int lenX = board[0].length;
//        int lenY = board.length;
//        Set<String> result = new HashSet<>();
//        for (int y = 0; y < lenY; y++) {
//            for (int x = 0; x < lenX; x++) {
//                search(board, x, y, lenX, lenY, root, new StringBuilder(), result);
//            }
//        }
//        return new ArrayList<>(result);
//    }
//
//    private void search(char[][] board, int x, int y, int lenX, int lenY, TrieNode root, StringBuilder thisWord, Set<String> result) {
//        if (x < 0 || x >= lenX || y < 0 || y >= lenY) return;
//        char c1 = board[y][x];
//        if (c1 == 0) return;
//        int position = c1 - 'a';
//        TrieNode node = root.childs[position];
//
//        if (node != null) {//exist prefix
//            node.exists = true;
//            thisWord.append(c1);
//            if (node.isWord) {
//                result.add(thisWord.toString());
//            }
//
//            if (node.childs == null) {//whole word exists
//                return;
//            }
//            board[y][x] = 0;
//            search(board, x + 1, y, lenX, lenY, node, thisWord, result);
//            search(board, x, y + 1, lenX, lenY, node, thisWord, result);
//            search(board, x - 1, y, lenX, lenY, node, thisWord, result);
//            search(board, x, y - 1, lenX, lenY, node, thisWord, result);
//            board[y][x] = c1;
//            thisWord.deleteCharAt(thisWord.length() - 1);
//        }
//    }

    private TrieNode createTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insertWord(root, word);
        }
        return root;
    }

    private void insertWord(TrieNode root, String word) {
        TrieNode parent = root;
        for (char c : word.toCharArray()) {
            int position = c - 'a';
            if (parent.childs == null) parent.childs = new TrieNode[26];
            TrieNode node = parent.childs[position];
            if (node == null) {
                node = new TrieNode();
                node.aChar = c;
                parent.childs[position] = node;
            }
            parent = node;
        }
        parent.isWord = true;
    }

    private class TrieNode {
        char aChar;
        TrieNode[] childs;
        boolean exists;
        boolean isWord;
    }
}
