package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_5 {

    /**
     * 面试题 02.05. <a href="https://leetcode.cn/problems/sum-lists-lcci/description/">链表求和</a>
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        给定两个用链表表示的整数，每个节点包含一个数位。
        这些数位是反向存放的，也就是个位排在链表首部。
        编写函数对这两个整数求和，并用链表形式返回结果。
         */
        /*
        迭代写法，将结果合并至 l1
         */
        int c = 0;
        ListNode t1 = l1, t2 = l2;
        ListNode pre = null;
        while (t1 != null) {
            int sum = c + t1.val + (t2 != null ? t2.val : 0);
            t1.val = sum % 10;
            c = sum / 10;
            pre = t1;
            t1 = t1.next;
            t2 = t2 != null ? t2.next : null;
            if (t1 == null) {
                pre.next = t2;
                t1 = t2;
                t2 = null;
            }
        }
        if (c > 0) {
            pre.next = new ListNode(c);
        }

        return l1;
    }

}
