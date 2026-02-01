package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_17 {

    /**
     * 3319. <a href="https://leetcode.cn/problems/k-th-largest-perfect-subtree-size-in-binary-tree/description/">第 K 大的完美二叉子树的大小</a> 1603
     * <p>
     * 完美二叉树 是指所有叶子节点都在同一层级的树，且每个父节点恰有两个子节点。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        /*
        题解思路: 完美二叉树子树大小为 2^h - 1

         */
        List<Integer> hs = new ArrayList<>();
        dfs(root, hs);
        if (k > hs.size()) {
            return -1;
        }
        Collections.sort(hs);
        return (1 << hs.get(hs.size() - k)) - 1;
    }

    private int dfs(TreeNode node, List<Integer> hs) {
        if (node == null) {
            return 0;
        }
        int leftH = dfs(node.left, hs);
        int rightH = dfs(node.right, hs);
        if (leftH < 0 || leftH != rightH) {
            return -1;
        }
        hs.add(leftH + 1);
        return leftH + 1;
    }

}
