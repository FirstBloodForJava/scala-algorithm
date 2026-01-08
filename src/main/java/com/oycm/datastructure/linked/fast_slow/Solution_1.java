package com.oycm.datastructure.linked.fast_slow;


import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 876. <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">链表的中间结点</a> 1232
     *
     * @param head
     * @return 返回链表的中间节点, 如果右两个中间节点, 则返回 第二个中间节点
     */
    public ListNode middleNode(ListNode head) {
        /*
        fast 比 slow 每次多移动一格;
        n 表示 head 的长度, fast 最多移动 n/2 次
        当 n 是偶数时, 第 1 + n/2 正好是中间节点
        当 n 是奇数时, 第 1 + n/2 正好时中间节点
         */
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
