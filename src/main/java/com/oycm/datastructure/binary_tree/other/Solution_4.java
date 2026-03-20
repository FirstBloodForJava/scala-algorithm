package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 655. <a href="https://leetcode.cn/problems/print-binary-tree/description/">输出二叉树</a>
     *
     * @param root 二叉树的根节点
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        /*
        构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res
            树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1
            矩阵的列数 n 应该等于 2 ^ (height+1) - 1
            根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2]
            对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，
                左子节点在 res[r+1][c - 2 ^ (height-r-1)]
                右子节点在 res[r+1][c + 2 ^ (height-r-1)]
         */
        /*
        先计算树的高度, 把矩阵填充好
         */
        int height = dfs(root);
        int n = (1 << height + 1) - 1;
        List<List<String>> res = new ArrayList<>(height + 1);
        for (int i = 0; i <= height; i++) {
            List<String> layer = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                layer.add("");
            }
            res.add(layer);
        }

        dfs(root, 0, (n - 1) / 2, res, height);

        return res;
    }

    public int dfs(TreeNode node) {
        if (node == null) return -1;
        int left = dfs(node.left);
        int right = dfs(node.right);
        return Math.max(left, right) + 1;
    }

    public void dfs(TreeNode node, int r, int c, List<List<String>> res, int height) {
        if (node == null) return;
        res.get(r).set(c, node.val + "");
        dfs(node.left, r + 1, c - (1 << height - r - 1), res, height);
        dfs(node.right, r + 1, c + (1 << height - r - 1), res, height);
    }

    public static void main(String[] args) {
        new Solution_4().printTree(new TreeNode(1, new TreeNode(2), null));
    }

}
