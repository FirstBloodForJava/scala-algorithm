package com.oycm.datastructure.linked.front_back;


import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 19. <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">删除链表的倒数第 N 个结点</a>
     * 删除链表的倒数第 n 个结点, 返回链表的头节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*
        两次遍历, 先计算链表的长度
         */
        int k = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            k++;
        }
        ListNode dummy = new ListNode(-1, head), cur = dummy;

        while (k > n) {
            cur = cur.next;
            k--;
        }

        cur.next = cur.next.next;

        return dummy.next;
    }

    public ListNode frontBack(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode left = dummy;
        ListNode right = dummy;
        // 右指针先走, 当 n 走到
        while (n-- > 0) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return dummy.next;
    }
}
