package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_13 {

    /**
     * 1373. <a href="https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/description/">二叉搜索子树的最大键值和</a> 1914
     *
     * @param root
     * @return
     */
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int ans;

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int x = node.val;
        // 不是二叉搜索树
        if (x <= left[1] || x >= right[0]) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }
        int sum = left[2] + right[2] + x;
        ans = Math.max(ans, sum);
        return new int[]{Math.min(left[0], x), Math.max(right[1], x), sum};
    }

}
