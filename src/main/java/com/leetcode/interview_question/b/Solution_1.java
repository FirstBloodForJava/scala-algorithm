package com.leetcode.interview_question.b;

import com.oycm.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 面试题 02.01. <a href="https://leetcode.cn/problems/remove-duplicate-node-lcci/description/">移除重复节点</a>
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        /*
        编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
         */
        /*
        hash 表记录已存在的节点
        删除当前节点，要知道前一个节点
         */
        Set<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = null;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            if (!set.add(cur.val)) {
                // 元素已存在，当前节点要删除
                pre.next = cur.next;
                continue;
            }
            pre = cur;
        }

        return dummy.next;
    }

}
