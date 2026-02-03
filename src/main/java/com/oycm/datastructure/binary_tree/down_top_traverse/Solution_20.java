package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_20 {

    /**
     * 1145. <a href="https://leetcode.cn/problems/binary-tree-coloring-game/description/">二叉树着色游戏</a> 1741
     * <p>
     *  最开始:
     *      一号 玩家从 [1, n] 中取一个值 x [1, n], x 节点染上红色;
     *      二号 玩家也从 [1, n] 中取一个值 y [1, n] 且 y != x, y 节点染上蓝色;
     * <p>
     *  每一轮:
     *      一号 玩家先手, 每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色
     *
     * @param root 二叉树共有 n 个节点, 且 n 为奇数, 每个节点上的值从 1 到 n 各不相同
     * @param n
     * @param x
     * @return
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        /*
        y 选择 x 的最近节点开始染色, 才是最优解, 这样就分成了 3 块 区域
        节点 x 左子树数量 ls; 节点 x 右子树数量 rs; 节点 x 的父节点数量 n - 1 - ls -rs
        n2max = max(ls, rs, n - 1 - ls - rs)
        n2 > n - n2 => 2 * n2 > n
         */
        this.x = x;
        dfs(root, x);
        int n2 = Math.max(ls, Math.max(rs, n - 1 - ls - rs));
        return n2 * 2 > n;
    }
    int ls, rs, x;

    public int dfs(TreeNode node, int x) {
        if (node == null) return 0;
        int left = dfs(node.left, x);
        int right = dfs(node.right, x);
        if (node.val == x) {
            ls = left;
            rs = right;
        }
        return 1 + left + right;
    }


}
