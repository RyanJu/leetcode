package com.zrk.leetcode.medium.AddTwoNumbers;

import java.util.List;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/8 11:22 1.0
 * @time 2018/1/8 11:22
 * @project leetcode com.zrk.leetcode.medium.AddTwoNumbers
 * @description You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * @updateVersion 1.0
 * @updateTime 2018/1/8 11:22
 */

public class Solution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);


        printList(new Solution().addTwoNumbers(l1, l2));
    }

    private static void printList(ListNode listNode) {
        StringBuilder stringBuilder = new StringBuilder();
        while (listNode != null) {
            stringBuilder.append(listNode.val).append(",");
            listNode = listNode.next;
        }
        System.out.println(stringBuilder.toString());
        return;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryBit = 0;
        ListNode head = null;
        ListNode next = null;
        while (l1 != null || l2 != null || carryBit != 0) {
            int a1 = l1 == null ? 0 : l1.val;
            int a2 = l2 == null ? 0 : l2.val;
            int sum = a1 + a2 + carryBit;
            int bit = sum % 10;
            carryBit = sum / 10;
            if (next == null) {
                next = new ListNode(bit);
            } else {
                next.next = new ListNode(bit);
                next = next.next;
            }
            if (head == null) {
                head = next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return head;
    }


}
