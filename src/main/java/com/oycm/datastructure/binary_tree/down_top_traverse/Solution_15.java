package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_15 {

    /**
     * 2265. <a href="https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree/description/">统计值等于子树平均值的节点数</a> 1473
     *
     * @param root
     * @return 求节点值 和 当前树和平均值相等的节点个数
     */
    public int averageOfSubtree(TreeNode root) {
        /*
        求 当前节点 和 当前树的平均值
        求 子树和的过程中, 同时记录元素和元素个数
         */
        dfs(root);
        return ans;
    }

    int ans = 0;

    public int[] dfs(TreeNode node) {
        int sum = node.val;
        int cnt = 1;
        if (node.left != null) {
            int[] left = dfs(node.left);
            sum += left[0];
            cnt += left[1];
        }
        if (node.right != null) {
            int[] right = dfs(node.right);
            sum += right[0];
            cnt += right[1];
        }
        if (node.val == sum / cnt) {
            ans++;
        }
        return new int[]{sum, cnt};
    }
}
