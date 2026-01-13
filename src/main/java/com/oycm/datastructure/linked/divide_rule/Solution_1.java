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

    public ListNode mergeKLists(ListNode[] lists, int i, int j) {
        /*
        题解思路: 递归合并
         */
        int m = j - i;
        if (m == 0) {
            return null;
        }
        if (m == 1) {
            return lists[i];
        }
        ListNode left = mergeKLists(lists, i, i + m / 2);
        ListNode right = mergeKLists(lists, i + m / 2, j);

        return mergeTwoLists(left, right);
    }

    // 合并有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(), cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;

        return dummy.next;
    }

    public ListNode iterateMergeKLists(ListNode[] lists) {
        /*
        题解思路-迭代合并:
        两两合并: 0,1 => 0; 2,3 => 2; 4,5 => 4; 6,7 => 6 ...
        四四合并: 0,2 => 2; 4,6 => 4; ...
        八八合并: 0,4 => 4 ...
        ...
         */
        int m = lists.length;
        if (m == 0) {
            return null;
        }
        for (int step = 1; step < m; step *= 2) {
            for (int i = 0; i < m - step; i += step * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + step]);
            }
        }

        return lists[0];
    }

}
