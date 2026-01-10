package com.oycm.datastructure.linked.merge;

import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 2. <a href="https://leetcode.cn/problems/add-two-numbers/description/">两数相加</a>
     *
     * @param l1 一个整数按逆序
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        迭代实现
         */
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int c = 0;
        while (l1 != null || l2 != null || c != 0) {
            if (l1 != null) {
                c += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                c += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(c % 10);
            cur = cur.next;
            c /= 10;
        }
        return dummy.next;
    }

}
