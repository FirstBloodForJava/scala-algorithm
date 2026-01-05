package com.oycm.datastructure.linked.reverse;

import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 206. <a href="https://leetcode.cn/problems/reverse-linked-list/description/">反转链表</a>
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
