package com.oycm.datastructure.linked.merge;


import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 2816. <a href="https://leetcode.cn/problems/double-a-number-represented-as-a-linked-list/description/">翻倍以链表形式表示的数字</a> 1394
     *
     * @param head
     * @return
     */
    public ListNode doubleIt(ListNode head) {
        head = reverse(head);
        ListNode dummy = new ListNode(), cur = dummy;
        int c = 0;
        while (head != null || c > 0) {
            if (head != null) {
                int dbl = head.val * 2 + c;
                cur.next = head;
                head.val = dbl % 10;
                c = dbl / 10;
                head = head.next;
                cur = cur.next;
            } else {
                cur.next = new ListNode(c);
                c = 0;
            }

        }

        return reverse(dummy.next);
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

}
