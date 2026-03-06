package com.oycm.datastructure.binary_tree.update;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 701. <a href="https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/">二叉搜索树中的插入操作</a>
     * <p>
     * 二叉搜索树: 左子树所有节点值 小于 root.val, 右子树所有节点值 大于 root.val, 且左右子树都是二叉搜索树
     *
     * @param root 二叉搜索树
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        /*
        判断当前节点和 val 的关系
            val < root.val, val 应该在 root 的左子树插入;
            val > root.val, val 应该在 root 的右子树插入;
            当前左子树(小于 root.val)/右子树(大于 root.val) 为空时，就是可以插入的点
         */
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (cur != null) {
            if (val < cur.val) {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }

        return root;
    }

}
