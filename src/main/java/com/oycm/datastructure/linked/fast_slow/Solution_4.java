package com.oycm.datastructure.linked.fast_slow;

import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 2130. <a href="https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/description/">链表最大孪生和</a> 1318
     *
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {
        /*
        找到中间节点，反转后面的节点，在重新遍历求最大值
         */
        int ans = 0;
        ListNode mid = findMid(head);

        ListNode h2 = reserveNode(mid);
        while (h2 != null) {
            ans = Math.max(head.val + h2.val, ans);
            head = head.next;
            h2 = h2.next;
        }

        return ans;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reserveNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
