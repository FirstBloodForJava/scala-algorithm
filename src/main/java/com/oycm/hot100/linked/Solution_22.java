package com.oycm.hot100.linked;

import com.oycm.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_22 {

    /**
     * 160. <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/description/">相交链表</a>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
        两个单链表的头节点 headA 和 headB
        找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
         */
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

}
