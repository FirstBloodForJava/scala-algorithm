package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 530. <a href="https://leetcode.cn/problems/minimum-absolute-difference-in-bst/description/">二叉搜索树的最小绝对差</a>
     *
     * @param root 二叉搜索树
     * @return 树中任意两不同节点值之间的最小差值
     */
    public int getMinimumDifference(TreeNode root) {
        /*
        题解思路: 二叉搜索树 中序遍历 得到的序列是有序的, 相邻两数差值求最小值, 即答案
         */
        dfs(root);
        return ans;
    }

    int ans = Integer.MAX_VALUE;
    int pre = Integer.MIN_VALUE / 2;

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        ans = Math.min(ans, node.val - pre);
        pre = node.val;
        dfs(node.right);
    }

}
