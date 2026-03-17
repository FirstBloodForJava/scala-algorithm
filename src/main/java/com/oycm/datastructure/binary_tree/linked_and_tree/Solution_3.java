package com.oycm.datastructure.binary_tree.linked_and_tree;

import com.oycm.ListNode;
import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 109. <a href="https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/">有序链表转换二叉搜索树</a>
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        /*
        题解思路: 和有序数组类似, 中间节点为 根节点
         */
        return buildTree(head, null);
    }

    /**
     * 快慢指针获取链表的中间节点
     *
     * @param left
     * @param right
     * @return
     */
    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        return new TreeNode(mid.val, buildTree(left, mid), buildTree(mid.next, right));
    }

}
