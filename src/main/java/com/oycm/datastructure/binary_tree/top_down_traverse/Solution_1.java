package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 104. <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">二叉树的最大深度</a>
     *
     * @param root
     * @return 根节点到最远叶子节点的最长路径上的节点数
     */
    public int maxDepth(TreeNode root) {
        /*
        自顶向下的过程, 每次更新答案
         */
        dfs(root, 0);
        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        depth++;
        ans = Math.max(ans, depth);
        dfs(node.left, depth);
        dfs(node.right, depth);
    }

}
