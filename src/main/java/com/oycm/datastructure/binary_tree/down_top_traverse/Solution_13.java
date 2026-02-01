package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_13 {

    /**
     * 563. <a href="https://leetcode.cn/problems/binary-tree-tilt/description/">二叉树的坡度</a>
     * <p>
     * 节点的坡度: 该节点左子树的节点之和和右子树节点之和的 差的绝对值
     * <p>
     * 叶子节点坡度为 0: 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        /*
        dfs 计算左右子树之和, 什么时候更新答案
         */
        dfs(root);
        return ans;
    }
    int ans = 0;

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        ans += Math.abs(right - left);
        return node.val + left + right;
    }

}
