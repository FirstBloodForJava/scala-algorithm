package com.oycm.datastructure.linked.merge;

import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 445. <a href="https://leetcode.cn/problems/add-two-numbers-ii/description/">两数相加 II</a>
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        return reverse(addTwoNumbers1(l1, l2));
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
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
