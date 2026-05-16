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
        最大路径和，可以是二叉树上任意一条链
        当前节点表示自底下的节点到当前路径的最大和（不一定是叶子节点，中间如果存在负数的情况，肯定不计算，和才能更大）：
            max(max(左子树的最大路径和 + 右子树的最大路径和) + node.val, 0)
            max(max(left, right) + node.val, 0)
        在返回最大链和前，先计算经过当前节点链的最大和，再更新下面节点到当前节点的最大和。
        递归边界：节点为空，返回 0；
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
