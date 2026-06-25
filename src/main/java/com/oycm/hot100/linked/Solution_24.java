package com.oycm.hot100.linked;

import com.oycm.ListNode;

public class Solution_24 {

    /**
     * 234. <a href="https://leetcode.cn/problems/palindrome-linked-list/description/">回文链表</a>
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        /*
        快慢指针：慢节点走一步，快节点走两步，快节点到中间，慢节点位于中间节点
        abccba
         ↑↑
          ↑ ↑
           ↑  ↑
        ababa
         ↑↑
          ↑ ↑
        上面通过快慢指针，找到 head 的中间节点
        下标从 0 开始，中间节点位置为 节点数量 / 2，abccba 中间节点下标为 3，ababa 中间节点下标为 2
        反转中间节点开始的链表
        从反转的链表开始遍历，看链表元素是否相等
         */
        /*
        递归，在归的过程，将 left 和 right 进行比较
         */
        left = head;
        return isPal(head);
    }
    ListNode left;

    public boolean isPal(ListNode right) {
        if (right.next != null && !isPal(right.next)) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        left = left.next;
        return true;
    }



    public void dfs(ListNode node) {
        if (node == null) return;
        // 在 dfs 之前打印，就是从左到右打印链表
        dfs(node.next);
        // 在 dfs 之后打印，就是从右到左打印链表
    }

    public boolean isPalindrome_1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时 slow 就是中间节点
        // 反转链表
        ListNode pre = null, cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        while (pre != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }

        return true;
    }


}
