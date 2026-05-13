package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_33 {

    /**
     * 148. <a href="https://leetcode.cn/problems/sort-list/description/">排序链表</a>
     *
     * @param head 节点数 [0, 5e4]
     * @return
     */
    public ListNode sortList(ListNode head) {
        /*
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
         */
        /*
        归并排序：
            1. 找到链表的中间节点 mid，断开 mid 与前一个节点的连接。这样就把原链表分成了两个链表。
            2. 分治，递归调用排序，分别排序 head 和 mid。
            3. 排序后，得到了两个有序链表，那么合并两个有序链表，就得到了排序后的链表，返回链表的头节点。
         */
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMidNode(head);

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);
        return mergeTwoSortNode(l1, l2);
    }

    public ListNode getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 中间节点的前一个节点
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        // 断开与中间节点的连接
        pre.next = null;
        return slow;
    }

    public ListNode mergeTwoSortNode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;

            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
