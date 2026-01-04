package com.oycm.datastructure.linked.delete;

import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 83. <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/">删除排序链表中的重复元素</a>
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

}
