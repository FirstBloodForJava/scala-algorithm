package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_6 {

    /**
     * 面试题 02.06. <a href="https://leetcode.cn/problems/palindrome-linked-list-lcci/description/">回文链表</a>
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        /*
        编写一个函数，检查输入的链表是否是回文的。
         */
        /*
        反转整个链表，头尾比较
        寻找中间节点，反转中间节点开始后的链表
         */
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
