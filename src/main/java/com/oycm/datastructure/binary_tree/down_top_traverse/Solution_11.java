package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_11 {

    /**
     * 2331. <a href="https://leetcode.cn/problems/evaluate-boolean-binary-tree/description/">计算布尔二叉树的值</a> 1304
     *
     * 二叉树叶子节点值为 0 或 1, 0 表示 false 1 表示 true; 非叶子节点值为 2 或 3, 2 表示 逻辑或 |, 3 表示逻辑与 &
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {
        /*
        完整二叉树: 每个节点有 0 或 2 个孩子的树
         */
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        return root.val == 2 ? left | right : left & right;
    }

}
