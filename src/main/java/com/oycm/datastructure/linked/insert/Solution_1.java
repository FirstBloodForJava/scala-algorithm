package com.oycm.datastructure.linked.insert;


import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 2807. <a href="https://leetcode.cn/problems/insert-greatest-common-divisors-in-linked-list/description/">在链表中插入最大公约数</a> 1279
     * <p>
     * 相邻结点之间，插入一个新的结点，结点值为这两个相邻结点值的 最大公约数
     *
     * @param head
     * @return
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {

        ListNode cur = head;
        while (cur.next != null) {
            // 执行插入
            ListNode temp = cur.next;
            cur.next = new ListNode(gcd(cur.val, cur.next.val), temp);
            cur = temp;
        }

        return head;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
