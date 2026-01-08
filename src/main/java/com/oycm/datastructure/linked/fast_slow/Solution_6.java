package com.oycm.datastructure.linked.fast_slow;

import com.oycm.ListNode;

public class Solution_6 {

    /**
     * 141. <a href="https://leetcode.cn/problems/linked-list-cycle/description/">环形链表</a>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
