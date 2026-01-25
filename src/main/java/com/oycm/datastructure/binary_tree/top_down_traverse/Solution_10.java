package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;

public class Solution_10 {

    /**
     * 1022. <a href="https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/description/">从根到叶的二进制数之和</a> 1462
     *
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        /*
        和 10 进制路径表示数字类似, ans += 叶子节点表示的数字
         */
        dfs(root, 0);
        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        k = (k << 1) + node.val;
        if (node.left == null && node.right == null) {
            ans += k;
        }
        dfs(node.left, k);
        dfs(node.right, k);
    }


}
