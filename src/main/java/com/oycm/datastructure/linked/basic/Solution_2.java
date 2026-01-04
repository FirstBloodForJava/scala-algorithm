package com.oycm.datastructure.linked.basic;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_2 {

    /**
     * 2058. <a href="https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/description/">找出临界点之间的最小和最大距离</a> 1311
     * <p>
     * 三个节点才能构成临界点: 局部极大值点 或 局部极小值点
     * 局部极大值点: 当前节点的值 严格大于 前一个节点和后一个节点
     * 局部极小值点: 当前节点的值 严格小于 前一个节点和后一个节点
     *
     * @param head
     * @return [minDistance, maxDistance] 求链表中不同临界点之间的最小距离, 最大距离
     */
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        /*
        3 个节点才能构成 临界点
         */
        int[] ans = new int[]{-1, -1};
        int minDistance = Integer.MAX_VALUE;
        int min = 0, preCur = 0;
        int n = 1;
        int pre = head.val;
        for (ListNode node = head.next; node.next != null; node = node.next, n++) {
            if ((node.val > pre && node.val > node.next.val) || node.val < pre && node.val < node.next.val) {
                if (min > 0) {
                    ans[1] = n - min;
                    minDistance = Math.min(minDistance, n - preCur);
                    ans[0] = minDistance;
                } else {
                    min = n;
                }
                preCur = n;
            }
            pre = node.val;

        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(nodesBetweenCriticalPoints(DataCreateUtils.buildListNode(new int[]{6, 8, 4, 1, 9, 6, 6, 10, 6}))[0]);
    }

}
