package com.oycm.month2026.august;

import com.oycm.ListNode;

public class Solution_31 {

    /**
     * 2058. <a href="https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/description/">找出临界点之间的最小和最大距离</a> 1311
     *
     * @param head
     * @return
     */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        /*
        链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点 。
        如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个  局部极大值点 。
        如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个  局部极小值点 。
        注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点 。
        给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance] ，
            其中 minDistance 是任意两个不同临界点之间的最小距离，
            maxDistance 是任意两个不同临界点之间的最大距离。如果临界点少于两个，则返回 [-1，-1] 。
         */
        /*
        最大距离：第一个临界点和最后一个临界点距离
        最小距离：不断求新的和前一个临界点距离最小值
         */
        int max = 0;
        int min = Integer.MAX_VALUE;
        ListNode pre = head, cur = head.next;
        int i = 1;
        int start = 0;

        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (pre.val < cur.val && cur.val > next.val || pre.val > cur.val && cur.val < next.val) {
                if (start != 0) {
                    max += i - start;
                    min = Math.min(min, i - start);

                }
                start = i;
            }
            i++;
            pre = cur;
            cur = next;
        }
        if (max == 0) {
            return new int[]{-1, -1};
        }

        return new int[]{min, max};
    }
}
