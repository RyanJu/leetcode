package com.zrk.leetcode.easy.ValidateBinarySearchTree;

import org.w3c.dom.NameList;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/5/16 15:15 1.0
 * @time 2018/5/16 15:15
 * @project leetcode com.zrk.leetcode.easy.ValidateBinarySearchTree
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/5/16 15:15
 */

class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }

        return isValidBST(root.right);
    }

    public static void main(String[] a) {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(7);
        root.right = new TreeNode(10);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(8);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(11);

        System.out.println("is validate: " + new Solution().isValidBST(root));

    }
}
