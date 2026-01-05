package com.oycm.datastructure.linked.insert;


import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 147. <a href="https://leetcode.cn/problems/insertion-sort-list/description/">对链表进行插入排序</a>
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        // lastSorted 前面有序的链表的尾节点, curr 表示需比较后插入的节点
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummy;
                // 寻找 curr 在 有序链表中间插入的位置, prev 为 curr 插入的前一个位置, 这里不需要判断 prev.next 节点是否为空, 因为 curr.val 一定会小于 有序链表中的一个节点, 提前结束循环
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next; // curr 要插在 prev 后面, 所有 prev.next 为 curr.next
                prev.next = curr; // prev 后插入 curr
            }
            curr = lastSorted.next;
        }
        return dummy.next;
    }

}
