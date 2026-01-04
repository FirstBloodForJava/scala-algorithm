package com.oycm.datastructure.linked.delete;

import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 82. <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/">删除排序链表中的重复元素 II</a>
     *
     * @param head
     * @return 删除原始链表中所有重复数字的节点
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        boolean isDel = false;
        while (cur.next != null) {
            if (cur.next.next != null && cur.next.val == cur.next.next.val) {
                // 元素相等
                isDel = true;
                cur.next = cur.next.next;
            } else if (isDel) {
                // cur.next 要删除
                cur.next = cur.next.next;
                isDel = false;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

}
