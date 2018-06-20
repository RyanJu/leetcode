package com.zrk.leetcode.homework;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/14 17:46 1.0
 * @time 2018/6/14 17:46
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/14 17:46
 */

public class RedBlackTree<T extends Comparable> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final Node NIL = new Node<>(null, BLACK);

    private Node<T> mRoot;

    private Comparator mComparator;

    public void insert(T t) {
        Node<T> node = mRoot;
        if (node == null) {
            mRoot = new Node<>(t, BLACK);
            return;
        }
        Node<T> p = null;
        while (node != NIL) {
            p = node;
            if (compare(node.data, t) < 0) {
                //t goes right
                node = node.right;
            } else {
                node = node.left;
            }
        }

        Node<T> z = new Node<T>(t, RED);
        z.parent = p;

        if (compare(p.data, t) < 0) {
            p.right = z;
        } else {
            p.left = z;
        }
        insertFixUp(z);
    }

    private void insertFixUp(Node<T> z) {
        while (z.parent != null && z.parent.color == RED) {
            Node<T> y;
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
            } else {
                y = z.parent.parent.left;
            }
            if (y.color == RED) {
                //case 1, father is red and uncle is red
                z.parent.color = y.color = BLACK;
                z.parent.parent.color = RED;
                z = z.parent.parent;
            } else {
                if (z == z.parent.right) {
                    //case 2 , father is red ,and uncle is black, and current is father's right child
                    z = z.parent;
                    leftRotate(z);
                    //turn to case 3
                }
                //case 3
                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                z = z.parent.parent;
                rightRotate(z);
            }
        }
        if (mRoot != null) {
            mRoot.color = BLACK;
        }
    }

    private void rightRotate(Node<T> z) {
        Node p = z.parent;
        Node<T> left = z.left;

        z.parent = left;
        z.left = left.right;
        if (left.right != null) {
            left.right.parent = z;
        }

        left.parent = p;
        left.right = z;
        if (p != null) {
            if (p.left == z) {
                p.left = left;
            } else {
                p.right = left;
            }
        } else {
            //root changed
            mRoot = left;
        }
    }

    private void leftRotate(Node<T> z) {
        Node p = z.parent;
        Node<T> right = z.right;
        z.parent = right;
        z.right = right.left;
        if (right.left != null) {
            right.left.parent = z;
        }

        right.parent = p;
        right.left = z;
        if (p != null) {
            if (p.left == z) {
                p.left = right;
            } else {
                p.right = right;
            }
        } else {
            //root changed
            mRoot = right;
        }
    }

    private int compare(Object o1, Object o2) {
        if (o1 != null && o1 instanceof Comparable) {
            return ((Comparable) o1).compareTo(o2);
        }
        if (mComparator != null) {
            return mComparator.compare(o1, o2);
        }
        return 0;
    }

    public void delete(T t) {

    }

    public T search(T t) {

        return null;
    }

    public void printTree() {
        Node node = mRoot;
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> tempQueue = new LinkedList<>();
        queue.addLast(node);
        while (!queue.isEmpty()) {
            tempQueue.clear();
            while (!queue.isEmpty()) {
                node = queue.removeFirst();
                if (node == null) {
                    continue;
                }
                if (node == NIL) {
                    continue;
                }

                String parent = "";
                String lr = "";
                String color = node.color == RED ? "red" : "black";

                if (node.parent == null) {
                    parent = "root";
                    lr = "root";
                } else {
                    parent = String.valueOf(node.parent.data);
                    lr = node.parent.left == node ? "left" : "right";
                }

                System.out.print("<" + node.data + "," + color + "," + parent + "'s " + lr + " child>   ");
                if (node.left != null) {
                    tempQueue.addLast(node.left);
                }
                if (node.right != null) {
                    tempQueue.addLast(node.right);
                }
            }
            System.out.println();

            queue.addAll(tempQueue);
        }
    }


    private static class Node<T> {
        boolean color;
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
            left = right = NIL;
        }

        public Node(T data, boolean color) {
            this.data = data;
            this.color = color;
            left = right = NIL;
        }
    }

}
