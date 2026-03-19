package com.oycm.datastructure.binary_tree.n_tree;

import com.oycm.Node;

public class Solution_3 {

    /**
     * 559. <a href="https://leetcode.cn/problems/maximum-depth-of-n-ary-tree/">N 叉树的最大深度</a>
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        dfs(root, 1);
        return ans;
    }
    int ans = 0;
    public void dfs(Node node, int depth) {
        if (node.children == null || node.children.isEmpty()) {
            ans = Math.max(ans, depth);
            return;
        }
        for (Node child : node.children) {
            dfs(child, depth + 1);
        }
    }

}
