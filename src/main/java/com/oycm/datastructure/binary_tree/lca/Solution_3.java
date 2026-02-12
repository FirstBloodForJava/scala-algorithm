package com.oycm.datastructure.binary_tree.lca;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 1123. <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/description/">最深叶节点的最近公共祖先</a> 1607
     *
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        /*
        题解思路:
            递的过程, 往下传 当前节点的深度
            归的过程, 返回当前子树的最大深度
            如果当前树的 左右节点最大深度相等, 则更新答案
         */
        dfs(root, 0);
        return ans;
    }

    TreeNode ans;
    int maxDepth = -1;

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            // 把空节点深度作为最大深度, 最深的空节点, 上一个节点一定是叶子节点
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        int leftDepth = dfs(node.left, depth + 1);
        int rightDepth = dfs(node.right, depth + 1);
        if (leftDepth == rightDepth && leftDepth == maxDepth) {
            ans = node;
        }
        return Math.max(leftDepth, rightDepth);
    }

}
