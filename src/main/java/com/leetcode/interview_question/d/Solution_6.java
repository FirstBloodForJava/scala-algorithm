package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

public class Solution_6 {

    /**
     * 面试题 04.06. <a href="https://leetcode.cn/problems/successor-lcci/description/">后继者</a>
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        /*
        设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
        如果指定节点没有对应的“下一个”节点，则返回 null。
         */
        /*
        root 中序遍历的下一个节点，如果 p 在遍历结果中，则返回 p 在中序遍历结果中的下一个节点
         */
        TreeNode next = null;
        // 二叉树右边存在节点，右子树的最左边节点则为下一个节点
        if (p.right != null) {
            next = p.right;
            while (next.left != null) {
                next = next.left;
            }
            return next;
        }
        // 右子树为空，在整棵树做二分查找
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                // p 在 node 的左子树
                next = node;
                node = node.left;
            } else {
                // p 在 node 的右子树，一直在右子树的情况下，下一个节点是空
                node = node.right;
            }
        }
        return next;
    }


}
