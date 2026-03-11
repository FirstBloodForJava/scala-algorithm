package com.oycm.datastructure.binary_tree.dp;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * LCP 34. <a href="https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/description/">二叉树染色</a>
     * <p>
     * 二叉树初始所有结点均为白色, 可以将白色染成蓝色, 节点的 val 表示价值
     *
     * @param root
     * @param k
     * @return 二叉树上每个蓝色相连部分的结点个数不能超过 k 个，求所有染成蓝色的结点价值总和最大
     */
    public int maxValue(TreeNode root, int k) {
        /*
        题解思路:
        后续遍历 + 状态转移
        先处理子树, 再合并结果到父节点
         */
        int[] ansPath = dfs(root, k);
        int ans = 0;
        for (int x : ansPath) {
            ans = Math.max(ans, x);
        }
        return ans;
    }

    private int[] dfs(TreeNode node, int k) {
        int[] dp = new int[k + 1];
        if (node == null) return dp;

        int[] left = dfs(node.left, k);
        int[] right = dfs(node.right, k);

        int leftMax = left[0];
        int rightMax = right[0];
        for (int i = 1; i < k; i++) {
            leftMax = Math.max(leftMax, left[i]);
            rightMax = Math.max(rightMax, right[i]);
        }

        // 当前节点不染色
        dp[0] = leftMax + rightMax;

        // 枚举当前节点染色长度不超过 k 的组合情况
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k - i; j++) {
                dp[i + j + 1] = Math.max(dp[i + j + 1], left[i] + right[j] + node.val);
            }
        }
        return dp;
    }


}
