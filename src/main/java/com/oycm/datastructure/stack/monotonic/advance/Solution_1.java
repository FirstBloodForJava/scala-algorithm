package com.oycm.datastructure.stack.monotonic.advance;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 1019. <a href="https://leetcode.cn/problems/next-greater-node-in-linked-list/description/">链表中的下一个更大节点</a> 1571
     *
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        int n = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        int[] ans = new int[n];
        int i = 0;
        while (head != null) {
            while (!stack.isEmpty() && head.val > stack.peek()[1]) {
                ans[stack.pop()[0]] = head.val;
            }
            stack.push(new int[]{i, head.val});
            head = head.next;
            i++;
        }

        return ans;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
