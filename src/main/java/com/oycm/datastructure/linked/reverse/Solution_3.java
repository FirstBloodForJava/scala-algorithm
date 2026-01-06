package com.oycm.datastructure.linked.reverse;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        // 下一个节点 和 下下节点存在 两两交换
        while (cur.next != null && cur.next.next != null) {
            // 第一个节点
            ListNode pre = cur.next;
            // 第二个节点
            ListNode suf = cur.next.next;
            // 下一轮开始的节点
            ListNode next = suf.next;
            // 交换
            cur.next = suf;
            cur.next.next = pre;
            // 下一轮循环
            cur = cur.next.next;
            cur.next = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(DataCreateUtils.listNodeToString(swapPairs(DataCreateUtils.buildListNode(new int[]{1, 2, 3, 4}))));
    }

}
