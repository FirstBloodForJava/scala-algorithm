package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 783. <a href="https://leetcode.cn/problems/minimum-distance-between-bst-nodes/description/">二叉搜索树节点最小距离</a> 1303
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    int ans = Integer.MAX_VALUE;
    int pre = Integer.MIN_VALUE / 2;

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        ans = Math.min(ans, node.val - pre);
        pre = node.val;
        dfs(node.right);
    }

}
