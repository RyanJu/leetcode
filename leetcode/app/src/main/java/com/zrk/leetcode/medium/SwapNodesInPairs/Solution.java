package com.zrk.leetcode.medium.SwapNodesInPairs;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/25 15:36 1.0
 * @time 2018/4/25 15:36
 * @project leetcode com.zrk.leetcode.medium.SwapNodesInPairs
 * @description Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 * <p>
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * @updateVersion 1.0
 * @updateTime 2018/4/25 15:36
 */

class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode first = head;
        ListNode second = null;
        ListNode prev = null;
        ListNode next = null;
        while (first != null) {
            second = first.next;
            if (second == null) {
                if (prev != null) {
                    prev.next = first;
                }
                break;
            }
            if (prev != null) {
                prev.next = second;
            }
            next = second.next;
            second.next = first;
            first.next = next;
            prev = first;
            first = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = null;
        ListNode node = null;
        ListNode prev = null;
        for (int i = 0; i < 7; i++) {
            node = new ListNode(i);
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
            if (head == null) {
                head = node;
            }
        }

        printNodes(head);
        printNodes(new Solution().swapPairs(head));
    }

    private static void printNodes(ListNode head) {
        System.out.print("\nhead: ");
        while (head != null) {
            System.out.print(head.val + "-->");
            head = head.next;
        }
        System.out.print(" end");
    }
}