package com.oycm.datastructure.linked.delete;


import com.oycm.ListNode;

public class Solution_5 {

    /**
     * 237. <a href="https://leetcode.cn/problems/delete-node-in-a-linked-list/description/">删除链表中的节点</a>
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        /*
        删除链表中给定的节点 node
         */
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
