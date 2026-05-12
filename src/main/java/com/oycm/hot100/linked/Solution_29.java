package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_29 {

    /**
     * 19. <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/">删除链表的倒数第 N 个结点</a>
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*
        给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
         */
        /*
        先计算链表的节点总数，记为 m，则 m - n - 1 后的节点就是要删除的节点
         */
        /*
        前后指针：first 指针先向前移动 n 次，first 和 second 同时向前移动，直到 first 移到空节点，此时 second 执行的节点就是需要删除的节点
         */
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        while (n-- > 0) {
            // 先向右移动 n 次
            first = first.next;
        }
        // second 距离 first n+1
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

}
