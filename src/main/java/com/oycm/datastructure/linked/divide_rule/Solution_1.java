package com.oycm.datastructure.linked.divide_rule;

import com.oycm.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_1 {

    /**
     * 23. <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">合并 K 个升序链表</a>
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        /*
        小顶堆存放 链表的头节点, 当堆不为空时
         */
        PriorityQueue<ListNode> min = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                min.add(head);
            }
        }
        ListNode dummy = new ListNode(), cur = dummy;
        while (!min.isEmpty()) {
            ListNode poll = min.poll();
            cur.next = poll;
            cur = cur.next;
            if (poll.next != null) {
                min.add(poll.next);
            }
        }
        return dummy.next;
    }

}
