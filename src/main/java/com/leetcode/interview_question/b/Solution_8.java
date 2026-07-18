package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_8 {

    /**
     * 面试题 02.08. <a href="https://leetcode.cn/problems/linked-list-cycle-lcci/description/">环路检测</a>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        /*
        给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
         */
        /*
        先判断是否存在环
         */
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (fast != head) {
                    fast = fast.next;
                    head = head.next;
                }
                return fast;
            }
        }

        return null;

    }

}
