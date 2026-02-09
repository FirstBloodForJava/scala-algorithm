package com.oycm.datastructure.binary_tree.recursion;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 538. <a href="https://leetcode.cn/problems/convert-bst-to-greater-tree/description/">把二叉搜索树转换为累加树</a>
     * 每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        /*
        二叉搜索树:
            节点的左子树仅包含键 小于 节点键的节点;
            节点的右子树仅包含键 大于 节点键的节点;
            左右子树也必须是二叉搜索树
        先递归右子树的和, 当前节点加上右子树的和就是大于等于当节点的和
         */
        dfs(root, 0);
        return root;
    }


    public int dfs(TreeNode node, int sum) {
        if (node == null) return sum;
        node.val += dfs(node.right, sum);
        return dfs(node.left, node.val);
    }


}
