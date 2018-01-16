package com.zrk.leetcode;

import java.util.List;

/**
 * Created by zhurongkun on 2017/9/19.
 */

public class TestCase {
    public static void main(String[] args) {
//        testWordSearch();
//        testWordSearch2();
        testCountTheRepetitions();
    }

    private static void testCountTheRepetitions() {
        /**
         * "baba"
         11
         "baab"
         1
         */
//        String s1 = "baba";
//        int n1 = 11;
//        String s2 = "baab";
//        int n2 = 1;


        /**
         * "phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenzkycxf"
         1000000
         "xtlsgypsfadpooefxzbcoejuvpvaboygpoeylfpbnpljvrvipyamyehwqnqrqpmxujjloovaowuxwhmsncbxcoksfzkvatxdknly"
         100
         */
        String s1 = "phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenzkycxf";
        int n1 = 1000000;
        String s2 = "xtlsgypsfadpooefxzbcoejuvpvaboygpoeylfpbnpljvrvipyamyehwqnqrqpmxujjloovaowuxwhmsncbxcoksfzkvatxdknly";
        int n2 = 100;

        long l = System.currentTimeMillis();
        int maxRepetitions = new CountTheRepetitions().getMaxRepetitions2(s1, n1, s2, n2);
        System.out.println(System.currentTimeMillis()-l);
        System.out.println(maxRepetitions);
    }

    private static void testWordSearch2() {
        WordSearch2 wordSearch2 = new WordSearch2();
//        char[][] board = {{'o', 'a', 'a', 'n'},
//                {'e', 't', 'a', 'e'},
//                {'i', 'h', 'k', 'r'},
//                {'i', 'f', 'l', 'v'}};
//        String[] words = {"aa","aaa","oath","pea","eat","rain"};
//        List<String> found = wordSearch2.findWords(new char[][]{{'a','a'}}, new String[]{"aaa"});
/**
 *  ["ab","aa"]
 ["aba","baa","bab","aaab","aaa","aaaa","aaba"]

 expect: ["aaa","aaab","aaba","aba","baa"]
 */
        char[][] board = {{'a', 'b'}, {'a', 'a'}};
        String[] words = {"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"};
        List<String> found = wordSearch2.findWords2(board, words);

        System.out.println(found);
    }

    private static void testWordSearch() {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        boolean exist = wordSearch.exist(board, "ABCCED");
        System.out.println("exist:" + exist);
    }
}
