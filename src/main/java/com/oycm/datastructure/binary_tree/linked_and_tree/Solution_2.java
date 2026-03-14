package com.oycm.datastructure.binary_tree.linked_and_tree;

import com.oycm.ListNode;
import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 1367. <a href="https://leetcode.cn/problems/linked-list-in-binary-tree/description/">二叉树中的链表</a>
     * <p>
     * 在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean dfs(ListNode listNode, TreeNode node) {
        if (listNode == null) return true;

        if (node == null) return false;

        if (listNode.val != node.val) return false;

        return dfs(listNode.next, node.left) || dfs(listNode.next, node.right);
    }

}
