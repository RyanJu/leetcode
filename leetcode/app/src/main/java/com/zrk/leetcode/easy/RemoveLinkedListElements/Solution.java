package com.zrk.leetcode.easy.RemoveLinkedListElements;

import java.util.List;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/8 15:54 1.0
 * @time 2018/4/8 15:54
 * @project leetcode com.zrk.leetcode.easy.RemoveLinkedListElements
 * @description Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * <p>
 * Credits:
 * Special thanks to @mithmatt for adding this problem and creating all test cases.
 * @updateVersion 1.0
 * @updateTime 2018/4/8 15:54
 */

public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(6);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);
        node = node.next;
        node.next = new ListNode(6);
        node = node.next;
        node.next = new ListNode(6);
        node = node.next;
        node.next = new ListNode(6);
        node = node.next;
        node.next = new ListNode(9);
        print(head);

        ListNode node1 = new Solution().removeElements(head, 6);


        print(node1);


    }

    private static void print(ListNode head) {
        ListNode listNode = head;
        while (listNode != null) {
            System.out.print(listNode.val + "-->");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode result = null;
        ListNode prev = null;
        while (head != null) {
            if (head.val != val) {
                if (result == null) {
                    result = head;
                }
                prev = head;
            } else {
                if (prev != null) {
                    prev.next = head.next;
                }
            }
            head = head.next;
        }
        return result;
    }
}
