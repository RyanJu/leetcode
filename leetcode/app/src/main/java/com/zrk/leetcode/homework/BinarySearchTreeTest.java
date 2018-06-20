package com.zrk.leetcode.homework;

import java.util.Comparator;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/1 10:43 1.0
 * @time 2018/6/1 10:43
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/1 10:43
 */

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int arr[] = {10, 4, 17, 1, 5, 16, 21, 0, 5, 6, 7, 13, 16};
        for (int a : arr) {
            tree.insert(a);
        }

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public boolean consume(Integer data) {
                System.out.print(data + "  ");
                return true;
            }
        };

        tree.travel(consumer);
        System.out.println();
        tree.travelInLevel(consumer);

        BinarySearchTree.LinkNode<Integer> linkNode = tree.toSortedLinkList();
        System.out.println("\n\nsorted link list");
        while (linkNode != null) {
            System.out.print(linkNode.getData() + " --> ");
            linkNode = linkNode.getRight();
        }
        System.out.println("\n\n");

        System.out.println();
        System.out.println("tree height = " + tree.getHeight());

        System.out.println();
        System.out.println("tree width = " + tree.getWidth());


        System.out.println();
        System.out.println("tree nodes count " + tree.getNodeCount());

        System.out.println();
        System.out.println("--------------");
        BinarySearchTree.Node<Integer> node = tree.search(17);
        System.out.println("search " + node);

        BinarySearchTree.Node<Integer> succesor = tree.succesor(17);
        System.out.println("successor " + succesor);

        BinarySearchTree.Node<Integer> predeccesor = tree.predeccesor(17);
        System.out.println("predeccessor " + predeccesor);


        System.out.println("removeTop " + tree.getMin());
        System.out.println("max " + tree.getMax());


        tree.delete(10);
        tree.travel(consumer);
        System.out.println();

        tree.delete(8);
        tree.travel(consumer);
        System.out.println();

        tree.delete(0);
        tree.travel(consumer);
        System.out.println();

        tree.delete(7);
        tree.travel(consumer);
        System.out.println();
    }


}
