package com.oycm.datastructure.linked.divide_rule;


import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 148. <a href="https://leetcode.cn/problems/sort-list/description/">排序链表</a>
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 查找中间节点
        ListNode h2 = middleNode(head);
        head = sortList(head);
        h2 = sortList(h2);
        // 合并前面排序后的链表
        return mergeTwoLists(head, h2);
    }

    // 查找中间节点, 并断开头节点和中间节点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开中间节点
        pre.next = null;

        return slow;
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


    public ListNode iterateSortList(ListNode head) {
        /*
        题解思路:
        可以理解为 23 题, head 长为 n, 长为 n, 长度为 1 的有序列表合并过程
        两两合并; 四四合并 的过程, 这个过程在 链表中维护
         */
        ListNode dummy = new ListNode(0, head);
        int length = getNodeLength(head);

        // step 为步长，即参与合并的链表长度: 1, 2, 4 ... 2^n
        for (int step = 1; step < length; step *= 2) {
            ListNode newListTail = dummy; // 新链表的末尾
            // 相当于 23 的 lists[0]
            ListNode cur = dummy.next;
            while (cur != null) {
                // 找出两段 step 长的节点,
                ListNode head1 = cur;
                ListNode head2 = splitList(head1, step);
                // 下一轮循环的起点, 以及切断 第二个 step 步长的节点
                cur = splitList(head2, step);
                // 第一轮是合并 长为至多 1 的链表
                ListNode[] merged = mergeTwoListsReturn(head1, head2);
                // 拆分后的链表重新调整指向, 尾部 -> 新头; 尾部 -> 新尾部;
                newListTail.next = merged[0];
                newListTail = merged[1];
            }
        }

        return dummy.next;
    }

    // 获取链表长度
    private int getNodeLength(ListNode head) {
        int n = 0;
        while (head != null) {
            n++;
            head = head.next;
        }
        return n;
    }

    private ListNode splitList(ListNode head, int size) {
        // 先找到 nextHead 的前一个节点
        ListNode cur = head;
        for (int i = 0; i < size - 1 && cur != null; i++) {
            cur = cur.next;
        }

        // 如果链表长度 <= size
        if (cur == null || cur.next == null) {
            return null; // 不做任何操作，返回空节点
        }

        ListNode nextHead = cur.next;
        // step 长切断节点
        cur.next = null;
        return nextHead;
    }

    // 合并有序链表, 并返回 [头节点, 尾节点]
    private ListNode[] mergeTwoListsReturn(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        while (cur.next != null) {
            cur = cur.next;
        }
        // 循环结束后，cur 是合并后的链表的尾节点
        return new ListNode[]{dummy.next, cur};
    }

}
