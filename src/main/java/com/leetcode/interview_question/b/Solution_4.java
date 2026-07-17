package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 面试题 02.04. <a href="https://leetcode.cn/problems/partition-list-lcci/description/">分割链表</a>
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        /*
        给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
        你不需要 保留 每个分区中各节点的初始相对位置。
         */
        /*
        两个指针，一个存小于 x 的节点 mn；一个存在大于等于 x 的节点 mx
        遍历后，mn 的尾部插入 mx 的头节点，返回 mn 头节点
        引入哨兵节点，原地修改，简化判断
         */
        ListNode mnDummy = new ListNode(0);
        ListNode mn = mnDummy;
        ListNode mxDummy = new ListNode(0);
        ListNode mx = mxDummy;

        while (head != null) {
            if (head.val < x) {
                mn.next = head;
                mn = mn.next;
            } else {
                mx.next = head;
                mx = mx.next;
            }
            head = head.next;
        }

        mn.next = mxDummy.next;
        // 如果是中间，要去掉后面的引用
        mx.next = null;

        return mnDummy.next;
    }

}
