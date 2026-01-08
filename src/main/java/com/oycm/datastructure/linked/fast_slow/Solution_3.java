package com.oycm.datastructure.linked.fast_slow;


import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 234. <a href="https://leetcode.cn/problems/palindrome-linked-list/description/">回文链表</a>
     * <p>
     * 回文序列: 序列是向前和向后读都相同的序列。aba
     *
     * @param head
     * @return 判断链表是否为 回文链表
     */
    public boolean isPalindrome(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转 中间节点及后面的链表
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        while (pre != null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;

        }

        return true;
    }

}
