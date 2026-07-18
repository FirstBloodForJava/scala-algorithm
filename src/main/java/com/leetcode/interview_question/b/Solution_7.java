package com.leetcode.interview_question.b;

import com.oycm.ListNode;

public class Solution_7 {

    /**
     * 面试题 02.07. <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/">链表相交</a>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
        给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
         */
        /*
        a1 -> a2
                         c1 -> c2 -> c3
        b1 -> b2 -> b3
        假设两条链表会相交，设 headA 到交点需要走 a 步，设 headB 到交点需要走 b 步，交点到终点（空节点）需要走 c 步。
            当 a = b 时，两个节点同时向前走，此时节点相等，此时该节点就是交点；
            当 a != b 时，一个节点走 a + c + b 步，一个节点走 b + c + a 布，此时两个走一样的步数，也是在交点相遇。
            headA 走到尽头，换成 headB 开始走，headB 走到尽头，换成 headA 开始走。
        如果链条链表没有相交，那么 a + b = b + a，链表最终会在空节点相遇
         */
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

}
