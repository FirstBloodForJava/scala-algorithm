package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_27 {

    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
         */
        /*
        哨兵节点+尾插法
        创建一个哨兵节点，作为合并后新链表的头节点的前一个节点。这样可以避免对 list1 list2 空节点的判断
        当 list1, list2 都不为空时
            如果 list1.val < list2.val，则把 list1 插入到链表的尾节点，把 list1 替换成下一个节点
            否则，把 list2 插入到链表的尾节点，把 list2 替换成下一个节点
        循环结束后，如果由不为空的节点，添加到新链表的末尾
         */
        /*
        递归，头插法
        merge(l1, l2)
        分类讨论：
            l1.val < l2.next，则等价于 l1.next = merge(l1.next, l2) 返回 l1
            l1.val >= l2.next，则等价于 l2.next = merge(l1, l2.next) 返回 l2
        递归边界：如果一个链表为空，返回另一个链表作为结果。

         */
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }

}
