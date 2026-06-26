package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_46 {

    /**
     * 114. <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/">二叉树展开为链表</a>
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        /*
        给你二叉树的根结点 root ，请你将它展开为一个单链表：
            展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
            展开后的单链表应该与二叉树 先序遍历 顺序相同。
         */
        /*
        root.right 指向 root.left，同时还要知道左子树的最后一个节点，最后一个节点要指向 root.right
        链表头插法：
            pre 是当前链表的头节点，node = new TreeNode(val, pre)，新的节点之前前一个头节点，pre = node
        1
       / \
      2   5
     / \   \
    3   4   6
        先序遍历的结果是 1 -> 2 -> 3 -> 4 -> 5 -> 6，看作反转后的链表结果
        6 -> 5 -> 4 -> 3 -> 2 -> 1 遍历结果使用头插法就是先序遍历二叉树得到的结果
        上面遍历的结果，是 右子树 -> 左子树 -> 根，遍历顺序，在递归的过程中执行头插法
         */
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        // 当前节点指向上一个头节点
        root.right = pre;
        // 更新头节点
        pre = root;
    }

    TreeNode pre = null;

}
