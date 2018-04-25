package com.zrk.leetcode.easy.MergeTwoSortedLists;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/16 17:53 1.0
 * @time 2018/1/16 17:53
 * @project leetcode com.zrk.leetcode.easy.MergeTwoSortedLists
 * @description Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * @updateVersion 1.0
 * @updateTime 2018/1/16 17:53
 */

public class Solution {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode listNode = new Solution().mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + "-->");
            listNode = listNode.next;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        ListNode rest = l1 != null ? l1 : (l2 != null ? l2 : null);
        current.next = rest;
        return dummy.next;
    }
}
