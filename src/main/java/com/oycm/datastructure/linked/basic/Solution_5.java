package com.oycm.datastructure.linked.basic;


import com.oycm.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_5 {

    /**
     * 817. <a href="https://leetcode.cn/problems/linked-list-components/description/">链表组件</a> 1429
     * <p>
     * nums 中组件: 链表中一段最长连续结点的值(必须在 nums 中) 构成的集合
     *
     * @param head 链表中节点 val 值唯一
     * @param nums 是 head 中 的子集
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        /*

         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int cnt = 0;
        int ans = 0;
        while (head != null) {
            if (set.contains(head.val)) {
                cnt++;
            } else if (cnt > 0) {
                cnt = 0;
                ans++;
            }
            head = head.next;
        }

        if (cnt > 0) {
            ans++;
        }
        return ans;
    }

}
