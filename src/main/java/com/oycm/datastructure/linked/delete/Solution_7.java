package com.oycm.datastructure.linked.delete;


import com.oycm.ListNode;

public class Solution_7 {

    /**
     * 2487. <a href="https://leetcode.cn/problems/remove-nodes-from-linked-list/description/">从链表中移除节点</a> 1455
     *
     * @param head
     * @return 移除每个右侧有一个更大数值的节点
     */
    public ListNode removeNodes(ListNode head) {
        /*
        头节点可能被删除, 减少判断 引入哨兵节点
        无法判断 中间节点小于 后面节点的数据
        题解思路-递归: 递归的本质就是倒序遍历 链表
        */
        if (head.next == null) {
            return head;
        }
        ListNode node = removeNodes(head.next);
        // 后面的节点更大
        if (node.val > head.val) {
            return node;
        }
        head.next = node;
        return head;
    }

    public ListNode rev(ListNode head) {
        ListNode rev = reverseNode(head);
        ListNode cur = rev;
        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return reverseNode(rev);
    }

    private ListNode reverseNode(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
