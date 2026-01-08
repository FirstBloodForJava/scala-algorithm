package com.oycm.datastructure.linked.fast_slow;

import com.oycm.ListNode;

public class Solution_5 {

    /**
     * 143. <a href="https://leetcode.cn/problems/reorder-list/description/">重排链表</a>
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        /*
        反转中间节点及其后面的节点, 把 中间节点前的下一个节点置为 null
         */
        ListNode h2 = reserveNode(findMid(head));
        while (head != null) {
            ListNode n1 = head.next;
            ListNode n2 = h2.next;
            head.next = h2;
            if (n1 != null) {
                h2.next = n1;
            }
            head = n1;
            h2 = n2;
        }
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }

    private ListNode reserveNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
