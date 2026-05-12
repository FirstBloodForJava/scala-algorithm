package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_28 {

    /**
     * 2. <a href="https://leetcode.cn/problems/add-two-numbers/description/">两数相加</a>
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。
         */
        /*
        递归，原地修改
         */
        return addTwo(l1, l2, 0);
    }

    public ListNode addTwo(ListNode l1, ListNode l2, int c) {
        if (l1 == null && l2 == null) return  c != 0 ? new ListNode(c) : null;

        if (l1 == null) {
            // l1 为 空时，能到这里，说明 l2 一定不为空
            l1 = l2;
            l2 = null;
        }
        int sum = c + l1.val + (l2 != null ? l2.val : 0);
        l1.val = sum % 10;
        l1.next = addTwo(l1.next, l2 == null ? null : l2.next, sum / 10);
        return l1;
    }

}
