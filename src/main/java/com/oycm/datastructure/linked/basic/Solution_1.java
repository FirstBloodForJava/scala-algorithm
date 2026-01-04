package com.oycm.datastructure.linked.basic;


import com.oycm.ListNode;

public class Solution_1 {

    /**
     * 1290. <a href="https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer/">二进制链表转整数</a> 1151
     *
     * @param head
     * @return
     */
    public static int getDecimalValue(ListNode head) {
        /*
        先计算链表的长度
         */
        int n = 0;
        for (ListNode node = head; node != null; node = node.next) {
            n++;
        }
        int ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            n--;
            ans |= node.val << n;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode sub1 = new ListNode(0);
        head.next = sub1;
        ListNode sub2 = new ListNode(1);
        sub1.next = sub2;

        System.out.println(getDecimalValue(head));
    }
}
