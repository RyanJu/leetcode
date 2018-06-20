package com.zrk.leetcode.medium.LinkedListCycleII;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/28 17:25 1.0
 * @time 2018/4/28 17:25
 * @project leetcode com.zrk.leetcode.medium.LinkedListCycleII
 * @description Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * @updateVersion 1.0
 * @updateTime 2018/4/28 17:25
 */

import java.util.List;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    /**
     * 第一步：快慢指针，快指针速度是慢指针的2倍，
     * 如果存在环，
     * 设 m=未进入环的节点数，n=环内节点数，x=慢指针步数
     * 当快慢指针重合时：
     * 快指针走了2x步，慢指针走了x步
     * 并且：（x-m）%n = (2x-m)%n
     * 显然x为n的倍数，x = kn（k为正整数）
     * 结论：快指针第一次追上慢指针时，慢指针走了正好k环的步数
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }
        Solution.ListNode first = head;
        Solution.ListNode second = head.next.next;
        int step = 1;
        boolean hasCycle = false;

        while (first != null && second != null && second.next != null) {
            if (first == second) {
                hasCycle = true;
                break;
            }
            first = first.next;
            second = second.next.next;
            step++;
        }

        if (!hasCycle)
            return null;

        first = head;
        second = head;
        for (int i = step - 1; i >= 0; i--) {
            first = goStep(first, i);
            second = goStep(second, i + step);
            if (first != second) {
                return first.next;
            }
        }
        return head;
    }

    private ListNode goStep(ListNode node, int step) {
        while (step > 0 && node != null) {
            node = node.next;
            step--;
        }
        return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(100);

        ListNode node0 = new ListNode(0);
        head.next = node0;

        ListNode node1 = new ListNode(1);
        node0.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        node6.next = node3;

        System.out.println(new Solution().detectCycle(head));

    }
}