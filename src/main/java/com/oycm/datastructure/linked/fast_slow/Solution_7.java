package com.oycm.datastructure.linked.fast_slow;

import com.oycm.ListNode;

public class Solution_7 {

    /**
     * 142. <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">环形链表 II</a>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        /*
        题解思路: 设 头节点到入环需要 a 步, 环长为 c
        设相遇时, 慢节点走了 b 步相遇, 则快指针走了 2b 步, 设快指针走了 k 圈, 此时 2b - b = kc, b = kc
        此时慢指针在 环中走了 b - a = kc - a, 再走 a 步, 就到入环口, 同时 head 也是再走 a 步到入环口, 所以不知道 a 是多少
        当 head 和 slow 相等时, 就是入环口
         */
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null;
    }

}
