package com.oycm.datastructure.binary_tree.down_top_traverse_del;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 1325. <a href="https://leetcode.cn/problems/delete-leaves-with-a-given-value/description/">删除给定值的叶子节点</a> 1407
     * 同 814 题目
     * @param root
     * @param target
     * @return
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

}
