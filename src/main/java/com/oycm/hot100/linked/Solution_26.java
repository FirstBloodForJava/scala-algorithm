package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_26 {

    /**
     * 142. <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">环形链表 II</a>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        /*
        给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
         */
        /*
        Floyd 判圈算法
        设头节点到入环口需要走 a 步。设环长为 c 步。
        设相遇时，慢指针走了 b 步，那么快指针走了 2b 步。
        设快慢指针相遇时，快指针比慢指针多走了 k 圈，2b - b = kc, b = kc
        慢指针从入环口开始，在环中走了 b - a = kc - a 步。从入环口开始，走 kc 步会回到入环口。
        所以，从相遇点开始，再走 a 步，就到了入环口，虽然不知道 a 步是多少。
        注意：
            从 head 开始，走 a 步会到入环口
            从 相遇点开始，走 a 步会到入环口
            相遇点和 head 同时一起走，会在走 a 步时相遇
         */
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return slow;
            }
        }

        return null;
    }

}
