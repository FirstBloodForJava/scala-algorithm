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
        1 -> 2 -> 3 -> 4
        倒数第 n 个节点，为从右往左数的节点数，则删除节点左边还有 m-n 个节点，那么 左边第 m-n-1 就是要删除节点的前一个节点。
        这里会删除头节点，加入哨兵节点，可以简化判断。
         */
        /*
        前后指针：first 指针先向前移动 n 次，first 和 second 同时向前移动，直到 first 移到空节点，此时 second 执行的节点就是需要删除的节点
        first 节点从 head 开始移动，
        second 节点从 哨兵节点开始移动，最终 second 的下一个节点就是要删除的节点
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
