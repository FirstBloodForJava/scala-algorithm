package com.oycm.datastructure.linked.integrate;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    /**
     * 1171. <a href="https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/">从链表中删去总和值为零的连续节点</a> 1782
     *
     * @param head
     * @return 反复删去链表中由 总和 值为 0 的连续节点组成的序列, 返回删除后的头节点
     */
    public static ListNode removeZeroSumSublists(ListNode head) {
        /*
        前 i 个节点的和 等于前 j 个节点的和 [i,j) 节点都可以删除
        前缀和 + hash 表
         */
        ListNode dummy = new ListNode(0, head);
        int pre = 0;
        Map<Integer,ListNode> map = new HashMap<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            pre += cur.val;
            map.put(pre, cur);
        }
        // 如果存在相同的前缀和, 则中间的节点都可以删除
        pre = 0;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            pre += cur.val;
            cur.next = map.get(pre).next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        removeZeroSumSublists(DataCreateUtils.buildListNode(new int[]{1,-1}));
    }

}
