package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

public class Solution_6 {

    /**
     * 222. <a href="https://leetcode.cn/problems/count-complete-tree-nodes/description/">完全二叉树的节点个数</a>
     *
     * @param root 完全二叉树
     * @return
     */
    public int countNodes(TreeNode root) {
        /*
        完全二叉树: 除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置
         */
        if (root == null) return 0;
        TreeNode lr = root, rr = root;
        int lh = 0, rh = 0;
        while (lr != null) {
            lh++;
            lr = lr.left;
        }
        while (rr != null) {
            rh++;
            rr = rr.right;
        }
        // 满二叉数
        if (lh == rh) {
            return (1 << lh) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


}
