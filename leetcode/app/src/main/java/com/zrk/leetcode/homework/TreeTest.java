package com.zrk.leetcode.homework;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/5/30 16:45 1.0
 * @time 2018/5/30 16:45
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/5/30 16:45
 */

class TreeTest {

    public static void main(String[] args) {
        TreeNode<Integer> root = createTestingBinarySearchTree();
        printTree(root);
        System.out.println();
        printTree2(root);

        System.out.println();
        inorderWalk(root);
        System.out.println();
        inorderWalkByIteration(root);
        System.out.println();
        inorderWalkByIteration2(root);
        System.out.println();
        inorderWalkByIterationNoStack(root);



    }

    private static void inorderWalkByIterationNoStack(TreeNode<Integer> root) {
        TreeNode<Integer> node = root;
        while (node != null) {
            node = node.left;
        }

        while (node != null) {
            System.out.print(node.data + "  ");

            TreeNode<Integer> p = node.parent;
            while (p.right == node) {
                p = p.parent;
                if (p == null) {
                    //backtrace to root ,return
                    return;
                }
            }

            if (p.right != null) {
                System.out.print(p.data + "  ");
                node = p.right;
                while (node != null && node.left != null) {
                    node = node.left;
                }
            } else {
                node = p;
            }
        }
    }


    private static void inorderWalkByIteration2(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();

        TreeNode<Integer> node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                TreeNode<Integer> pop = stack.pop();
                if (pop != null) {
                    System.out.print(pop.data + "  ");
                    node = pop.right;
                }
            }
        }
    }

    private static void inorderWalkByIteration(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        TreeNode<Integer> prev = null;
        while (!stack.isEmpty()) {
            TreeNode<Integer> pop = stack.pop();
            if (pop == null) {
                continue;
            }
            if (hasWalked(prev, pop) || (pop.left == null && pop.right == null)) {
                System.out.print(pop.data + "  ");
                prev = pop;
            } else {
                stack.push(pop.right);
                stack.push(pop);
                stack.push(pop.left);
            }
        }
    }

    private static boolean hasWalked(TreeNode<Integer> prev, TreeNode<Integer> pop) {
        while (prev != null) {
            if (prev == pop) {
                return true;
            }
            prev = prev.parent;
        }
        return false;
    }

    private static void inorderWalk(TreeNode<Integer> root) {
        if (root != null) {
            inorderWalk(root.left);
            System.out.print(root.data + "  ");
            inorderWalk(root.right);
        }
    }

    private static void printTree2(TreeNode<Integer> root) {
        List<TreeNode<Integer>> stack = new ArrayList<>();
        stack.add(root);

        TreeNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.remove(stack.size() - 1);
            if (curr == null) {
                continue;
            }
            System.out.print(curr.data + "  ");
            if (curr.right != null) {
                stack.add(curr.right);
            }
            if (curr.left != null) {
                stack.add(curr.left);
            }
        }

    }

    private static void printTree(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + "  ");
            printTree(root.left);
            printTree(root.right);
        }
    }

    private static class TreeNode<T> {
        T data;
        TreeNode<T> parent;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data, TreeNode<T> parent) {
            this.data = data;
            this.parent = parent;
        }

        public TreeNode(T data, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{node= " + data + " }";
        }

        public TreeNode<T> createLeft(T data) {
            return this.left = new TreeNode<T>(data, this);
        }

        public TreeNode<T> createRight(T data) {
            return this.right = new TreeNode<T>(data, this);
        }
    }

    static TreeNode<Integer> createTestingBinarySearchTree() {
        TreeNode<Integer> root = new TreeNode<Integer>(10, null);

        TreeNode<Integer> t = root.createLeft(4);
        t.createLeft(1).createLeft(0);
        t = t.createRight(5);
        t.createLeft(5);
        t.createRight(6);

        t = root.createRight(17);
        t.createLeft(16);
        t.createRight(21);


        return root;
    }


    static <T> TreeNode<T> createTree(Factory<T> factory, int count) {
        if (count <= 0) {
            return null;
        }

        TreeNode<T> root = new TreeNode<>(factory.createNext(), null);

        createTree(factory, count, root, 1);

        return root;
    }

    private static <T> int createTree(Factory<T> factory, int count, TreeNode<T> root, int counter) {
        if (counter >= count || root == null) {
            return counter;
        }

        Random random = new Random();
        boolean hasL = true;
        boolean hasR = true;
        if (hasL) {
            counter++;
            root.left = new TreeNode<T>(factory.createNext(), root);
        }
        if (hasR) {
            counter++;
            root.right = new TreeNode<T>(factory.createNext(), root);
        }

        counter = createTree(factory, count, root.left, counter);
        counter = createTree(factory, count, root.right, counter);
        return counter;
    }


    private interface Factory<T> {
        T createNext();
    }


    private static class RandomFactory implements Factory<Integer> {
        Random mRandom = new Random();

        @Override
        public Integer createNext() {
            return mRandom.nextInt(100);
        }
    }


    private interface Consumer<T> {
        void consume(T t);
    }

    private static class Printer<T> implements Consumer<T> {
        @Override
        public void consume(T t) {
            System.out.println("" + t + "  ");
        }
    }


    private static class Stack<T> extends LinkedList<T> {
        public Stack() {
        }

        public Stack(@NonNull Collection c) {
            super(c);
        }
    }

}
