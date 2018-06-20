package com.zrk.leetcode.easy.LinkedListCycle;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/28 17:27 1.0
 * @time 2018/4/28 17:27
 * @project leetcode com.zrk.leetcode.easy.LinkedListCycle
 * @description given a linked list, determine if it has a cycle in it.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * @updateVersion 1.0
 * @updateTime 2018/4/28 17:27
 */
class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode first = head;
        ListNode second = head.next;
        while (first != null && second != null && second.next != null) {
            if (first == second) {
                return true;
            }
            first = first.next;
            second = second.next.next;
        }
        return false;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;
        ListNode node5 = new ListNode(6);
        node4.next = node5;
        ListNode node6 = new ListNode(7);
        node5.next = node6;
        node6.next = null;

        System.out.println(new Solution().hasCycle(head));

    }

}