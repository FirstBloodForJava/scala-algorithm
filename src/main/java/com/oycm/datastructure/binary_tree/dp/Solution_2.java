package com.oycm.datastructure.binary_tree.dp;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 968. <a href="https://leetcode.cn/problems/binary-tree-cameras/description/">监控二叉树</a> 2124
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        /*
        ints.length = 3
        ans[0] 表示 当前节点安装摄像头
        ans[1] 表示 父节点安装摄像头
        ans[2] 表示 子节点安装摄像头
         */
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[2]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        // 当前节点安装监控
        int choose = Math.min(left[0], left[1]) + Math.min(right[0], right[1]) + 1;
        // 父节点安装监控
        int byFa = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        int byChildren = Math.min(Math.min(left[0] + right[2], left[2] + right[0]), left[0] + right[0]);
        return new int[]{choose, byFa, byChildren};
    }

}
