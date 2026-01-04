package com.oycm.datastructure.linked.delete;


import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 203. <a href="https://leetcode.cn/problems/remove-linked-list-elements/description/">移除链表元素</a>
     *
     * @param head
     * @param val
     * @return 移除链表中等于 val 的节点
     */
    public ListNode removeElements(ListNode head, int val) {
        /*
        题解思路:
        1 -> 2 -> 3 链表要想删除 2, 必须在节点 1 处操作, 把节点 1 的 next 指向 节点 3
        由于头节点可能被删除, 在头节点前面添加一个哨兵节点, 无需判断头节点被删除的情况, 简化代码逻辑
        增加一个 哨兵节点
         */
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                // 删除下一个节点
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

}
