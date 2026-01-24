package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_4 {

    int ans = 0;
    /**
     * 129. <a href="https://leetcode.cn/problems/sum-root-to-leaf-numbers/description/">求根节点到叶节点数字之和</a>
     * <p>
     * 二叉树中每个节点都存放有一个 0 到 9 之间的数字, 1 -> 2 -> 3 表示数字 123
     *
     * @param root
     * @return 计算 根节点到叶子节点生成的 所有数字之和
     */
    public int sumNumbers(TreeNode root) {
        /*
        1 -> 2 -> 3 1 * 100 + 2 * 10 + 3 * 1 = 123
        从 头往往下遍历 怎么知道前面需要乘以多少？
         */
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        k = k * 10 + node.val;
        // 叶子节点
        if (node.left == null && node.right == null) {
            ans += k;
            return;
        }
        dfs(node.left, k);
        dfs(node.right, k);
    }

}
