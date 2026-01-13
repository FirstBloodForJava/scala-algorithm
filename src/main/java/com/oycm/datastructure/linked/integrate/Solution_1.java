package com.oycm.datastructure.linked.integrate;


import com.oycm.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_1 {

    /**
     * 1019. <a href="https://leetcode.cn/problems/next-greater-node-in-linked-list/description/">链表中的下一个更大节点</a> 1571
     *
     * @param head
     * @return ans[i] 表示第 i 个节点, 下一个更大的值, 没有则返回 0
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head.next == null) {
            return new int[]{0};
        }
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        int[] ans = new int[n];
        /*
        怎么知道后面哪个值大于 第 i 个节点的值?
        小顶堆, 存 i, 和 i 的值
         */
        PriorityQueue<int[]> min = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; head != null; i++, head = head.next) {
            while (!min.isEmpty() && head.val > min.peek()[1]) {
                int[] poll = min.poll();
                ans[poll[0]] = head.val;
            }
            min.add(new int[]{i, head.val});
        }

        return ans;
    }

}
