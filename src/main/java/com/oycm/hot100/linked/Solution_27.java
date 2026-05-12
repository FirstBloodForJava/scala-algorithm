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

    }

}
