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

    public ListNode getIntersectionNode_point(ListNode headA, ListNode headB) {
        /*
        题解思路
        分类讨论：
        链表 headA 有 n 个节点，链表 headB 有 m 个节点
        如果两个链表相交，假设 headA 不相交部分有 a 个节点，链表 headB 不相交部分有 b 个节点，两个链表相交部分有 c 个节点，
            则有 a + c = n, b + c = m
            如果 a == b，headA, headB 同时往后走，两个指针会同时到达相交的节点
            如果 a != b，观察到 a + c + b == b + c + a
                headA 移动 a + c 次时，当前节点是空节点，把当前节点设置为 headB 的头节点，再移动 b 次，就到达了相交节点
                同理
                headB 移动 b + c 此时，当前节点是空节点，把当前节点设置为 headA 的头节点，再移动 a 次，就到达了相交节点
        如果两个链表不相交：
            如果 m == n，headA, headB 同时往后走，两个节点会在空节点相遇
            如果 m != n，观察到 m + n = n + m，这里和 a != b 节点相交同理，最终会在空节点相交
            headA 移动 m 次到达空姐点，节点置为 headB，再移动 n 次到达 空节点
         */
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

}
