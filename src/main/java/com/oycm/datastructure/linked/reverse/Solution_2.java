package com.oycm.datastructure.linked.reverse;

import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 92. <a href="https://leetcode.cn/problems/reverse-linked-list-ii/description/">反转链表 II</a>
     *
     * @param head
     * @param left
     * @param right
     * @return 反转 [left, right] 之间的节点
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        int n = 1;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (left == n) {
                // cur.next 后到 right 的节点需要反转
                ListNode pre = null, inner = cur.next, tail = inner;
                while (n <= right) {
                    ListNode next = inner.next;
                    inner.next = pre;
                    pre = inner;
                    inner = next;
                    n++;
                }
                tail.next = inner;
                cur.next = pre;
                break;
            }
            cur = cur.next;
            n++;
        }

        return dummy.next;
    }

}
