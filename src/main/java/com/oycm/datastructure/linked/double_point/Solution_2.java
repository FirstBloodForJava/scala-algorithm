package com.oycm.datastructure.linked.double_point;

import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 86. <a href="https://leetcode.cn/problems/partition-list/description/">分隔链表</a>
     *
     * @param head
     * @param x
     * @return 小于 x 的节点都出现在 大于或等于 x 的节点之前
     */
    public ListNode partition(ListNode head, int x) {
        /*
        题解思路: 新建两个链表 sml 记录 < x 的元素链表; lar 记录 >= x 的元素链表
         */
        ListNode sml = new ListNode(0);
        ListNode lar = new ListNode(0);
        ListNode smlHead = sml;
        ListNode larHead = lar;
        while (head != null) {
            if (head.val < x) {
                sml.next = head;
                sml = sml.next;
            } else {
                lar.next = head;
                lar = lar.next;
            }
            head = head.next;
        }
        sml.next = larHead.next;
        // lar 的 next 如果小于 x 则这里还有有指向, 所以需要设置 null
        lar.next = null;
        return smlHead.next;
    }

}
