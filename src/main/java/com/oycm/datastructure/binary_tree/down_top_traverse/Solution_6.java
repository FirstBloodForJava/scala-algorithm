package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_6 {

    /**
     * 951. <a href="https://leetcode.cn/problems/flip-equivalent-binary-trees/description/">翻转等价二叉树</a> 1477
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        /*
        和 971 有点类似, 如果 root1 和 root2 节点值相同, 则判断他们的子节点是否相同
            root1 != root2 二叉树如何翻转都不会相等
            root1 == root2 == null
            左右节点判断是否相等
         */
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.left, root1.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root1.right) && flipEquiv(root1.right, root2.left));
    }

}
