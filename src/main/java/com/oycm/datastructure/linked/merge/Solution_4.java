package com.oycm.datastructure.linked.merge;


import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 21. <a href="https://leetcode.cn/problems/merge-two-sorted-lists/description/">合并两个有序链表</a>
     *
     * @param list1 升序链表
     * @param list2 升序链表
     * @return 合并两个升序链表至一个链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        遍历 l1 l2,
            如果 l1 < l2, 指向 l1 节点, l1 指向下一个节点;
            如果 l1 > l2 ,指向 l1 节点, l1 指向下一个节点;
            相等的时候, 规定指向 l2 节点
        遍历后, 可能一个节点为空, 另外一个节点还有 剩余元素, 需要拼接在后面
         */
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

    public ListNode mergeRecursion(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeRecursion(list1.next, list2);
            return list1;
        }
        list2.next = mergeRecursion(list1, list2.next);
        return list2;
    }
}
