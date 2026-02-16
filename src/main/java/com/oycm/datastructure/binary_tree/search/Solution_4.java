package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_4 {

    /**
     * 938. <a href="https://leetcode.cn/problems/range-sum-of-bst/description/">二叉搜索树的范围和</a> 1335
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        /*
        可以中序遍历 [] 累加在 这个访问的值
         */
        dfs(root, low, high);

        return ans;
    }
    int ans = 0;

    private void dfs(TreeNode node, int low, int high) {
        if (node == null) return;
        if (node.val > high) {
            dfs(node.left, low, high);
        } else if (node.val < low) {
            dfs(node.right, low, high);
        } else {
            ans += node.val;
            dfs(node.left, low, high);
            dfs(node.right, low, high);
        }
    }

}
