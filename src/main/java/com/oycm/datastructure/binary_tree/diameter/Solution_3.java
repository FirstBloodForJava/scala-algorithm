package com.oycm.datastructure.binary_tree.diameter;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 124. <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/">二叉树中的最大路径和</a>
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        /*
        当前节点 左子树的最大路径和 + 右子树的最大路径和
        子树路径和为负数, 返回 0
        返回给父节点的最大路径和 max(left, right) + node.val, 0
         */
        dfs(root);
        return ans;
    }

    int ans = Integer.MIN_VALUE;

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        ans = Math.max(ans, left + right + node.val);
        return Math.max(Math.max(left, right) + node.val, 0);
    }

}
