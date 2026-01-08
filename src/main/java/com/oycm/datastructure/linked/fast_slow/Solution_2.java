package com.oycm.datastructure.linked.fast_slow;


import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 2095. <a href="https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/description/">删除链表的中间节点</a> 1324
     *
     * @param head
     * @return 删除链表的中间节点, 如果是偶数, 删除后面的节点
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 下一个 slow 是要删除的节点
        slow.next = slow.next.next;

        return head;
    }

}
