package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

public class Solution_5 {

    /**
     * 979. <a href="https://leetcode.cn/problems/distribute-coins-in-binary-tree/description/">在二叉树中分配硬币</a> 1709
     *
     * @param root n 个结点的二叉树, 二叉树 val 表示硬币的数量, val 和 为 n
     * @return 使每个结点上 只有 一枚硬币所需的 最少 移动次数
     */
    public int distributeCoins(TreeNode root) {
        /*
        移动规则: 父节点 -> 子节点, 或 子节点 -> 父节点
         */
        /*
        题解思路: 看当前节点及子树的硬币数 coins, 当前节点及子树节点数 nodes
            abs(coins - nodes) 就是 要经过 node 边的数量, 答案就是所有节点该值的和
            coins = l_coins + r_coins + node.val;
            nodes = l_nodes + r_nodes + 1
            d = coins - nodes
              = l_coins + r_coins + node.val - (l_nodes + r_nodes + 1)
              = (l_coins - l_nodes) + (r_coins - r_nodes) + node.val - 1
              = l_d + r_d + node.val - 1
         根节点没有父节点， 为什么不需要特别判断, 因为 nodes == coins
         */
        dfs(root);
        return ans;
    }

    int ans = 0;

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int d = dfs(node.left) - dfs(node.right) + node.val - 1;
        ans += Math.abs(d);
        return d;
    }

}
