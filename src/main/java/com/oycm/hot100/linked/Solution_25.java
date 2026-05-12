package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_25 {

    /**
     * 141. <a href="https://leetcode.cn/problems/linked-list-cycle/description/">环形链表</a>
     *
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        /*
        给你一个链表的头节点 head ，判断链表中是否有环。
         */
        /*
        快慢指针：如果存在环，快指针一定会在环中追上慢指针
         */
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

}
