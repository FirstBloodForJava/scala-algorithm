package com.oycm.datastructure.binary_tree.diameter;

import com.oycm.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution_4 {

    /**
     * 2385. <a href="https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/description/">感染二叉树需要的总时间</a> 1711
     *
     * @param root
     * @param start
     * @return
     */
    public int amountOfTime(TreeNode root, int start) {
        /*
        本质是求经过二叉树 root 中 start 节点的最长单边路径
        start 在 二叉树中共有三种情况
            start 在二叉树的根节点, 二叉树的最大高度
            start 在二叉树的左子树, 右子树高度 + 根节点到 start 节点高度, start 节点到叶子节点高度
            start 在二叉树的右子树, 左子树高度 + 根节点到 start 节点高度, start 节点到叶子节点高度
        这个思路不对, 也可能是 start 节点的 父节点拐弯处很长，导致答案错误
        题解思路: DFS 找到 start 节点, 同时用 哈希表 记录每个节点的父节点,
        从 start 节点出发, 求 这棵树的最大深度
         */
        dfs(root, null, start);
        return maxDepth(startNode, startNode);

    }

    private TreeNode startNode;
    private final Map<TreeNode, TreeNode> fa = new HashMap<>();

    private void dfs(TreeNode node, TreeNode from, int start) {
        if (node == null) {
            return;
        }
        // 记录每个节点的父节点
        fa.put(node, from);
        if (node.val == start) {
            startNode = node;
        }
        dfs(node.left, node, start);
        dfs(node.right, node, start);
    }

    private int maxDepth(TreeNode node, TreeNode from) {
        if (node == null) {
            return -1;
        }
        int res = -1;
        // 左节点
        if (node.left != from) {
            res = Math.max(res, maxDepth(node.left, node));
        }
        // 右节点
        if (node.right != from) {
            res = Math.max(res, maxDepth(node.right, node));
        }
        // 父节点
        if (fa.get(node) != from) {
            res = Math.max(res, maxDepth(fa.get(node), node));
        }
        return res + 1;
    }


    public int amountOfTime2(TreeNode root, int start) {
        /*
        问题拆分成 两个问题
        start 节点 的最大深度
        把 start 节点变成叶子节点, 求二叉树的最大直径
         */
        dfs(root, start);
        return ans;
    }

    private int ans;

    private int[] dfs(TreeNode node, int start) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] leftRes = dfs(node.left, start);
        int[] rightRes = dfs(node.right, start);
        int lLen = leftRes[0], lFound = leftRes[1];
        int rLen = rightRes[0], rFound = rightRes[1];
        if (node.val == start) {
            // 计算子树 start 的最大深度
            // 注意这里和方法一的区别，max 后面没有 +1，所以算出的也是最大深度
            ans = Math.max(lLen, rLen);
            return new int[]{1, 1}; // 找到了 start
        }
        // 找到 start 节点时, start 变成叶子节点, 才能更新二叉树的最大直径答案
        if (lFound == 1 || rFound == 1) {
            ans = Math.max(ans, lLen + rLen);
            // 保证 start 是直径端点
            return new int[]{(lFound == 1 ? lLen : rLen) + 1, 1};
        }
        return new int[]{Math.max(lLen, rLen) + 1, 0};
    }


}
