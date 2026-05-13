package com.oycm.hot100.linked;

import com.oycm.ListNode;

import java.util.PriorityQueue;

public class Solution_34 {

    /**
     * 23. <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">合并 K 个升序链表</a>
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        /*
        给你一个链表数组，每个链表都已经按升序排列。
        请你将所有链表合并到一个升序链表中，返回合并后的链表。
         */
        /*
        要从 lists 选出一个最小的节点，插入到 cur 的末尾，更新 lists 当前最小节点
        用一个有序集合存所有链表的头节点，当集合数量大于 1 时，选出移除最小的节点，进行操作，
         */
        PriorityQueue<ListNode> minNode = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                minNode.add(node);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (minNode.size() > 1) {
            ListNode next = minNode.poll();
            cur.next = next;
            cur = cur.next;
            next = next.next;
            if (next != null) {
                minNode.add(next);
            }
        }
        cur.next = minNode.isEmpty() ? null : minNode.poll();

        return dummy.next;
    }

    public ListNode mergeKLists_iterator(ListNode[] lists) {
        /*
        分治合并
        在 lists 数组中分治合并；
            下标间隔为 1 的元素，两两合并，合并后的链表放在前一个下标处 (0, 1) => 1; (2, 3) => 2 ...
            下标间隔为 2 的元素，两两合并，合并后的链表放在前一个下标处 (0, 2) => 0; (4, 6) => 4 ...
            下标间隔为 4 的元素，两两合并，合并后的链表放在前一个下标处 (0, 4) => 0; (8, 12) => 8 ...
            直到间隔 >= lists.length，lists[0] 就是合并好的链表
         */
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        for (int step = 1; step < n; step *= 2) {
            // 避免 (i, i + step) 要合并，下标不能越界
            for (int i = 0; i < n - step; i += step * 2) {
                lists[i] = mergerSortedList(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }

    public ListNode mergerSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
