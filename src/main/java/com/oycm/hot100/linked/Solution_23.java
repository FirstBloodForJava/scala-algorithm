package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_23 {

    /**
     * 206. <a href="https://leetcode.cn/problems/reverse-linked-list/description/">反转链表</a>
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        /*
        给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
         */
        /*
        递归实现：
            递归到链表的末尾节点，作为新链表的头节点，在归的过程，把前面的节点插在下一个节点的尾部，同时避免形成环
         */
        // head == null 是对初始链表为空姐点的判断
        if (head == null || head.next == null) {
            return head;
        }
        // 新链表的头节点
        ListNode revHead = reverseList(head.next);
        /*
        a -> b -> ... -> head -> head.next
        a -> b -> c -> d -> e -> f
        revHead = f, head.next == f
        head.next 和 head 方向要反过来 头 -> 尾 变成 尾 -> 头
         */
        ListNode tail = head.next;
        // 头 -> 尾 变成 尾 -> 头
        tail.next = head;
        // 避免形成环
        head.next = null;
        return revHead;
    }

    public ListNode reverseList_1(ListNode head) {
        /*
        迭代，尾插法
         */
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

}
