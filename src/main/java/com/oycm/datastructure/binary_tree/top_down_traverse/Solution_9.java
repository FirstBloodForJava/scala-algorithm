package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;

public class Solution_9 {

    /**
     * 1026. <a href="https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/description/">节点与其祖先之间的最大差值</a> 1446
     *
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        /*
        递归到左右子树 传递一个到当前节点的最大值, 最小值
         */
        dfs(root, root.val, root.val);
        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node, int max, int min) {
        /*
        递归到叶子节点时 max 和 min 是在一条路径上的, 所有可以在最后更新答案
         */
        if (node == null) {
            ans = Math.max(ans, max - min);
            return;
        }
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        dfs(node.left, max, min);
        dfs(node.right, max, min);

    }

}
