package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

public class Solution_4 {

    /**
     * 面试题 04.04. <a href="https://leetcode.cn/problems/check-balance-lcci/description/">检查平衡性</a>
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        /*
        实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
         */
        /*
        自底向上
         */
        return dfs(root) != -1;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        // 不合法，提前返回
        if (left == -1) return -1;
        int right = dfs(node.right);
        if (right == -1 || Math.abs(right - left) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


}
