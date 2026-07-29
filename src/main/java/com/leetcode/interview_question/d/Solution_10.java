package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

public class Solution_10 {

    /**
     * 面试题 04.10. <a href="https://leetcode.cn/problems/check-subtree-lcci/description/">检查子树</a>
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        }
        return isSameTree(t1, t2) ||
                checkSubTree(t1.left, t2) ||
                checkSubTree(t1.right, t2);
    }

    // 100. 相同的树
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q; // 必须都是 null
        }
        return p.val == q.val &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }


}
