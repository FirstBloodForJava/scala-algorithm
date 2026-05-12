package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_30 {

    /**
     * 24. <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/description/">两两交换链表中的节点</a>
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        /*
        给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
         */
        /*ListNode dummy = new ListNode(0, head);
        ListNode node0 = dummy;
        ListNode node1 = head;
        *//*
        0 -> 1 -> 2 -> 3 -> 4
        0 -> 2
        2 -> 1
        1 -> 3
        下一轮 1 -> 3 -> 4
         *//*
        while (node1 != null && node1.next != null) {
            ListNode node2 = node1.next;
            ListNode node3 = node2.next;
            node0.next = node2;
            node2.next = node1;
            node1.next = node3;
            // 下一轮交换
            node0 = node1;
            node1 = node3;
        }

        return dummy.next;*/
        /*
        递归
        递归边界，剩余节点数量不足两个，返回当前节点
         */
        if (head == null || head.next == null) return head;
        ListNode node2 = head.next;
        ListNode node3 = node2.next;
        // 2 -> 1 1 -> 下一轮返回
        node2.next = head;
        head.next = swapPairs(node3);

        return node2;
    }

}
