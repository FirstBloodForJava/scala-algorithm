package com.oycm.datastructure.binary_tree.dp;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 337. <a href="https://leetcode.cn/problems/house-robber-iii/description/">打家劫舍 III</a>
     * <p>
     * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警
     *
     * @param root 二叉树
     * @return  在不触动警报的情况下 ，小偷能够盗取的最高金额
     */
    public int rob(TreeNode root) {
        /*
        3
       / \
      4   5
     / \   \
    1   3   1
        选第一层 第二层就不能选
        题解思路
        选或不选:
            选当前节点: 左右儿子节点都不能选
            不选当前节点: 左右儿子节点可选可不选
        提炼状态:
            选当前节点, 以当前节点为根子树的 最大点权和
            不选当前节点,  以当前节点为根子树的 最大点权和
        转移方程:
            选当前节点 = 不选左子节点 + 不选右子节点 + 当前节点
            不选当前节点 = max(选左子节点, 不选左子节点) + max(选右子节点, 不选右子节点)
         */
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        return new int[]{left[1] + right[1] + node.val, Math.max(left[0], left[1]) + Math.max(right[0], right[1])};
    }

}
