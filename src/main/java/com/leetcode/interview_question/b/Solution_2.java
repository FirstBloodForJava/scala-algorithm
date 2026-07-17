package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 面试题 02.02. <a href="https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/description/">返回倒数第 k 个节点</a>
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        /*
        实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
         */
        /*
        前后指针
            前指针先走 k 个节点，两个指针同时往前走，当前指针走到空节点是，后指针当前位置就是倒数第 k 个节点。
         */
        ListNode front = head;
        /*
        往前走 k 个节点，算是 head 节点，假设 front 表示链表的尾节点，k 从 1 开始，那么 head 节点将表示倒数第 k + 1 节点。
        如果去掉 head 节点，就是 head 和 front 同时往前走一步，此时的 head 节点就是倒数第 k 个节点
         */
        while (k-- > 0) {
            front = front.next;
        }
        ListNode back = head;
        while (front != null) {
            front = front.next;
            back = back.next;
        }

        return back.val;
    }

}
