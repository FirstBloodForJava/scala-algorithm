package com.oycm.datastructure.linked.basic;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 2181. <a href="https://leetcode.cn/problems/merge-nodes-in-between-zeros/description/">合并零之间的节点</a> 1333
     *
     * @param head
     * @return
     */
    public static ListNode mergeNodes(ListNode head) {
        ListNode tail = head;
        for (ListNode cur = head.next; cur.next != null; cur = cur.next) {
            if (cur.val != 0) {
                tail.val += cur.val;
            } else {
                tail = tail.next;
                tail.val = 0;
            }
        }
        tail.next = null;

        return head;
    }

    public static void main(String[] args) {
        System.out.println(DataCreateUtils.listNodeToString(mergeNodes(DataCreateUtils.buildListNode(new int[]{0, 3, 1, 0, 4, 5, 2, 0}))));
    }

}
