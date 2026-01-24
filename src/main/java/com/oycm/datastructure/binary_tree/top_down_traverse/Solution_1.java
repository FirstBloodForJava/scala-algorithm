package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 104. <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">二叉树的最大深度</a>
     *
     * @param root
     * @return 根节点到最远叶子节点的最长路径上的节点数
     */
    public int maxDepth(TreeNode root) {
        /*
        可以转换成子问题: 左子树/右子树 的 最大长度, 加 1 就是 当前子树的最大深度
         */
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right= maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

}
