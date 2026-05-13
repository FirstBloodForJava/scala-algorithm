package com.oycm.hot100.linked;

import com.oycm.ListNode;
import com.oycm.utils.DataCreateUtils;

public class Solution_31 {

    /**
     * 25. <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/description/">K 个一组翻转链表</a>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        /*
        链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
        k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
         */
        /*
        前后指针，first 先向左移动 k - 1, second 到 first 直接的节点就是需要翻转的链表
         */
        if (k == 1) return head;
        int cnt = 0;
        ListNode dummy = new ListNode(0, head);
        ListNode second = dummy;
        ListNode first = head;
        while (first != null) {
            cnt++;
            if (cnt == k) {
                // 反转 [second.next -> first] 区间链表
                ListNode pre = first.next;
                ListNode cur = second.next;
                ListNode tail = cur;
                while (cur != first) {
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                cur.next = pre;
                second.next = cur;
                // second 要是反转后的倒数第二节点
                second = tail;
                first = second;
                cnt = 0;

            }
            first = first.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Solution_31 solution = new Solution_31();
        solution.reverseKGroup(DataCreateUtils.buildListNode(new int[]{1, 2, 3, 4, 5, 6}), 3);
    }

}
