package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

public class Solution_5 {

    /**
     * 面试题 04.05. <a href="https://leetcode.cn/problems/legal-binary-search-tree-lcci/">合法二叉搜索树</a>
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        /*
        实现一个函数，检查一棵二叉树是否为二叉搜索树。
         */
        /*
        中序遍历结果为升序数组
         */
        if (root == null) return true;
        // 不满足要求，直接返回
        if (!isValidBST(root.left)) {
            return false;
        }
        // 左子树结果大于等于当前节点
        if (pre >= root.val) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

    long pre = Long.MIN_VALUE;

}
