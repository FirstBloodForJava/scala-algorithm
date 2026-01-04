package com.oycm.datastructure.linked.delete;

import com.oycm.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_2 {

    /**
     * 3217. <a href="https://leetcode.cn/problems/delete-nodes-from-linked-list-present-in-array/description/">从链表中移除在数组中存在的节点</a> 1342
     *
     * @param nums
     * @param head
     * @return
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        /*
        移除 head 在 nums 中存在的节点, 同 203
         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (set.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

}
