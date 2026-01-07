package com.oycm.datastructure.linked.front_back;


import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 61. <a href="https://leetcode.cn/problems/rotate-list/description/">旋转链表</a>
     * 将链表每个节点向右移动 k 个位置
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        /*
        先求链表的长度 n, k % n 的结果就是 链表每个节点需要移动的次数
         */
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        // 后面的节点需要移动到前面
        int cnt = k % n;
        if (cnt == 0) {
            return head;
        }
        // 需要后移的节点
        int pre = n - cnt;
        ListNode dummy = new ListNode(-1, head);
        ListNode left = head;
        ListNode right = dummy;
        while (pre-- > 0) {
            right = right.next;
        }
        ListNode temp = right.next;
        dummy.next = temp;
        right.next = null;
        right = temp;
        while (right.next != null) {
            right = right.next;
        }
        right.next = left;

        return dummy.next;
    }

    public ListNode answer(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode next = head;
        while (next.next != null) {
            next = next.next;
            n++;
        }
        int mov = n - k % n;
        if (mov == n) {
            return head;
        }
        // 构成环
        next.next = head;
        while (mov-- > 0) {
            next = next.next;
        }
        ListNode ans = next.next;
        next.next = null;
        return ans;
    }

}
