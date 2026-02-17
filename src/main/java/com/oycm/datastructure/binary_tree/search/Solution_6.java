package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_6 {

    /**
     * 230. <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/">二叉搜索树中第 K 小的元素</a>
     *
     * @param root 二叉搜索树
     * @param k
     * @return 查找其中第 k 小的元素
     */
    public int kthSmallest(TreeNode root, int k) {
        /*
        中序遍历
         */
        this.k = k;
        return ans;
    }
    int k = 0;
    int ans = 0;

    private void dfs(TreeNode node) {
        if (node == null || k == 0)  return;
        dfs(node.left);
        if (--k == 0) {
            ans = node.val;
        }
        dfs(node.right);
    }

}
