package com.oycm.datastructure.linked.double_point;

import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 328. <a href="https://leetcode.cn/problems/odd-even-linked-list/description/">奇偶链表</a>
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        // 奇数节点
        ListNode odd = head;
        ListNode even = evenHead;
        while (even != null && even.next != null) {
            // 偶数 的下一个节点就是 奇数
            odd.next = even.next;
            odd = odd.next;
            // 偶数的 下一个 就是奇数
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
