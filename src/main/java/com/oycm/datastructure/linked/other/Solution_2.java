package com.oycm.datastructure.linked.other;

import com.oycm.ListNode;

import java.util.Random;

/**
 * 382. <a href="https://leetcode.cn/problems/linked-list-random-node/description/">链表随机节点</a>
 *
 */
public class Solution_2 {

    ListNode head;
    Random random;

    public Solution_2(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        /*
        题解思路: 水塘抽样
        P(第 i 个节点的值成为最后被返回的值)
        = P(第 i 次随机选择的值=0) x P(第 i+1 次随机选择的值=0) x ... x P(第 n 次随机选择的值=0)
        = 1/i x (1-1/i+1) x ... x (1 - 1/n)
        = 1/n
         */
        int i = 1, ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) {
                ans = node.val;
            }
            i++;
        }
        return ans;
    }

}
