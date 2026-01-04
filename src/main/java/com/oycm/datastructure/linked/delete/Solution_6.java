package com.oycm.datastructure.linked.delete;

import com.oycm.ListNode;

public class Solution_6 {

    /**
     * 1669. <a href="https://leetcode.cn/problems/merge-in-between-linked-lists/description/">合并两个链表</a> 1428
     *
     * @param list1 n 个元素
     * @param a >= 1
     * @param b < n-1
     * @param list2 m 个元素
     * @return 删除 list1 中 [a, b] 的节点, 拼接 list2 节点
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        /*
        头节点不会被删除
         */
        int i = 0;
        ListNode cur = list1;
        while (cur != null) {
            i++;
            // cur.next 节点要删除
            if (a == i) {
                ListNode suf = cur.next;
                // 指向 list2 的头节点
                cur.next = list2;
                // 查找 list2 的尾节点
                while (cur.next != null) {
                    cur = cur.next;
                }
                // 找到 list1 b 的 下一个节点
                while (i <= b) {
                    suf = suf.next;
                    i++;
                }
                cur.next = suf;
                break;
            } else {
                cur = cur.next;
            }

        }

        return list1;
    }


}
