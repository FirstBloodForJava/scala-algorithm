package com.oycm.datastructure.binary_tree.backtracking;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 1457. <a href="https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree/description/">二叉树中的伪回文路径</a> 1405
     * <p>
     * 伪回文: 路径经过的所有节点值的排列中，存在一个回文序列
     *
     * @param root
     * @return 根到叶子节点的所有路径中 伪回文 路径的总数
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        /*
        回文序列: 自由一个 数字出现了奇数次
         */
        dfs(root);
        return ans;
    }

    int ans = 0;
    int[] cnt = new int[10];

    public void dfs(TreeNode node) {
        if (node == null) return;
        cnt[node.val]++;
        if (node.left == null && node.right == null) {
            // 判断是否为 回文序列
            int i = 0;
            for (int c : cnt) {
                i += c % 2;
            }
            if (i <= 1) {
                ans++;
            }
        } else {
            dfs(node.left);
            dfs(node.right);
        }
        // 回溯
        cnt[node.val]++;
    }

    public int dfs(TreeNode node, int mask) {
        if (node == null) return 0;
        mask ^= 1 << node.val;
        if (node.left == null && node.right == null) {
            return (mask & (mask - 1)) == 0 ? 1 : 0;
        }
        return dfs(node.left, mask) + dfs(node.right, mask);
    }

}
