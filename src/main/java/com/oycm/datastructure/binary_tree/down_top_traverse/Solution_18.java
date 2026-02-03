package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_18 {

    /**
     * 1339. <a href="https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree/description/">分裂二叉树的最大乘积</a> 1675
     *
     * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
     *
     * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
     * @param root
     * @return
     */
    public int maxProduct(TreeNode root) {
        /*
        第一次 DFS, 先对整棵树求和 total
        第二次 DFS, 求当前子树的和 ans = Math.max(ans, s * (total - s))
         */
        int total = dfsTotal(root);
        dfs(root, total);
        return (int) (ans % 1000000007);
    }

    long ans = 0L;

    public int dfsTotal(TreeNode node) {
        if (node == null) return 0;
        return node.val + dfsTotal(node.left) + dfsTotal(node.right);
    }

    public int dfs(TreeNode node, int total) {
        if (node == null) {
            return 0;
        }
        int s = node.val + dfs(node.left, total) + dfs(node.right, total);
        ans = Math.max(ans, (long) s * (total - s));
        return s;
    }

}
