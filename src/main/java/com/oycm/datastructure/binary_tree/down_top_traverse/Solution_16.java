package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_16 {

    /**
     * 1026. <a href="https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/description/">节点与其祖先之间的最大差值</a> 1446
     *
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return ans;
    }
    int ans = 0;
    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int min = Math.min(node.val, Math.min(left[0], right[0]));
        int max = Math.max(node.val, Math.max(left[1], right[1]));
        ans = Math.max(ans, Math.max(node.val - min, max - node.val));

        return new int[]{min, max};
    }

}
