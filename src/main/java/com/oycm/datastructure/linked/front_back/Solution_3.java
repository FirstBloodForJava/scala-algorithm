package com.oycm.datastructure.linked.front_back;

import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 1721. <a href="https://leetcode.cn/problems/swapping-nodes-in-a-linked-list/description/">交换链表中的节点</a> 1378
     *
     * @param head
     * @param k
     * @return 交换 链表正数第 k 个节点和倒数第 k 个节点的值, 返回交换后的头节点
     */
    public ListNode swapNodes(ListNode head, int k) {
        /*
        k = 1, 正数第一个节点的值 和 倒数第一个节点的值 交换
         */
        ListNode dummy = new ListNode(-1, head);
        ListNode left = dummy;
        ListNode right = dummy;
        while (k-- > 0) {
            left = left.next;
        }
        ListNode leftTemp = left;
        while (left.next != null) {
            left = left.next;
            right = right.next;
        }
        // right 下一个节点才是倒数第 k 个节点
        int temp = leftTemp.val;
        leftTemp.val = right.next.val;
        right.next.val = temp;
        return dummy.next;
    }

}
