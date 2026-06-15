package com.oycm.month2026.june;

import com.oycm.ListNode;

public class Solution_15 {

    /**
     * 2095. <a href="https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/description/">删除链表的中间节点</a> 1324
     *
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        /*
        给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
        长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
        对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
         */
        /*
        快慢指针，当 n = 1, 2, 3, 4, 5 是，中间节点的下标就是 0, 1, 1, 2, 2
        只需要知道中间节点的前一个节点，就能删除中间节点了，为了能删除 n = 1 的情况，引入哨兵节点
         */
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 删除中间节点，中间节点的前一个节点指向下下个节点
        pre.next = pre.next.next;

        return dummy.next;
    }

}
