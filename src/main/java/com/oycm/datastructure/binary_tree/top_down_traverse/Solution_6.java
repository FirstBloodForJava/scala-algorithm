package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_6 {

    /**
     * 1448. <a href="https://leetcode.cn/problems/count-good-nodes-in-binary-tree/description/">统计二叉树中好节点的数目</a> 1360
     * <p>
     * 好节点: 从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值
     *
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        /*
        递归过程中维护一个 根节点到当前节点的最大值, 如果当前接待你 大于等于 max, 当前节点是好节点
         */
        return dfs(root, root.val);
    }

    public int dfs(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left, Math.max(max, node.val));
        int right = dfs(node.right, Math.max(max, node.val));
        return left + right + (max <= node.val ? 1 : 0);
    }

}
