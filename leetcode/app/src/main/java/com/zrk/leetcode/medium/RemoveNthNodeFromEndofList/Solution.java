package com.zrk.leetcode.medium.RemoveNthNodeFromEndofList;


/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/16 16:25 1.0
 * @time 2018/1/16 16:25
 * @project leetcode com.zrk.leetcode.medium.RemoveNthNodeFromEndofList
 * @description Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * @updateVersion 1.0
 * @updateTime 2018/1/16 16:25
 */

public class Solution {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = head;
//        for (int i = 2; i <= 5; i++) {
//            next.next = new ListNode(i);
//            next = next.next;
//        }
        printNode(head);
        System.out.println();
        ListNode node = new Solution().removeNthFromEnd2(head, 1);
        printNode(node);
    }

    private static void printNode(ListNode head) {
        ListNode next = head;
        while (next != null) {
            System.out.print(next.val + " --> ");
            next = next.next;
        }
    }


    /**
     * 使用快慢指针，快指针比满指针先走n步
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fakeHead = new ListNode(Integer.MAX_VALUE);
        fakeHead.next = head;
        ListNode fast = fakeHead;
        ListNode slow = fakeHead;

        for (int i = 0; i < n && fast.next != null; i++, fast = fast.next) ;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow != null && slow.next != null) {
            slow.next = slow.next.next;
        }
        return fakeHead.next;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fakeHead = new ListNode(Integer.MAX_VALUE);
        fakeHead.next = head;
        helper(fakeHead, 0, n);
        return fakeHead.next;
    }

    private int helper(ListNode node, int current, int nth) {
        int sum = current;
        if (node.next != null) {
            sum = helper(node.next, current + 1, nth);
        }
        if (current + nth == sum) {
            if (node.next != null) {
                node.next = node.next.next;
            }
        }
        return sum;
    }

}
