package com.oycm.datastructure.binary_tree.recursion;

import com.oycm.TreeNode;

public class Solution_4 {

    /**
     * 1080. <a href="https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/description/">根到叶路径上的不足节点</a> 1805
     * <p>
     * 不足节点: 根-叶 路径上值的总和 全都 小于给定的 limit 的节点
     *
     * @param root
     * @param limit
     * @return
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        /*
        一个节点可能多个 根-叶 路径通过，如果所有的 根-叶 路径和都 小于 limit, 则 node 需要删除
        如果 node 是叶子节点, 路径和小于 limit, 则叶子节点删除; 如果路径和 大于 limit, 叶子节点不用删除;
        如果 node 是非叶子节点, 如果它的 左右节点都删除了, 则说明路径和 都小于 limit, 该节点需要删除
         */
        limit -= root.val;
        if (root.left == null && root.right == null) {
            // 叶子节点
            // 大于 0, 说明 根-叶 的总合小于 limit, 要删除
            return limit > 0 ? null : root;
        }
        if (root.left != null) {
            root.left = sufficientSubset(root.left, limit);
        }
        if (root.right != null) {
            root.right = sufficientSubset(root.right, limit);
        }
        return root.left == null && root.right == null ? null : root;
    }

}
