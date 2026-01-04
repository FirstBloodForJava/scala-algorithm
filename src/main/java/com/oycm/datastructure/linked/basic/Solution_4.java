package com.oycm.datastructure.linked.basic;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 725. <a href="https://leetcode.cn/problems/split-linked-list-in-parts/description/">分隔链表</a>
     * <p>
     * 将链表分隔成 k 个连续的部分, 要求如下：
     * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode[] splitListToParts(ListNode head, int k) {
        /*
        先计算链表的长度
         */
        int n = 0;
        for (ListNode node = head; node != null; node = node.next) {
            n++;
        }
        ListNode[] nodes = new ListNode[k];
        if (n <= k) {
            for (int i = 0; i < n; i++) {
                nodes[i] = head;
                head = head.next;
                nodes[i].next = null;
            }
        } else {
            // n > k 前面的多分 n % k 分配 n/k + 1 个, 后面的分配 n/k 个
            int d = n / k, pre = n % k;
            for (int i = 0; i < k; i++) {
                nodes[i] = head;
                for (int j = 1; j < d + ((pre > 0) ? 1 : 0); j++) {
                    head = head.next;
                }
                pre--;
                ListNode tail = head;
                head = head.next;
                tail.next = null;
            }
        }

        return nodes;
    }

    public static void main(String[] args) {
        splitListToParts(DataCreateUtils.buildListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 3);
    }
}
