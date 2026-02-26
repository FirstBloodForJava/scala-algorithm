package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_7 {

    /**
     * 98. <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/">验证二叉搜索树</a>
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /*
    前序遍历: 当前节点是否满足 大于最小值，小于最大值
     */
    private boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        long x = node.val;
        return left < x && x < right &&
                isValidBST(node.left, left, x) &&
                isValidBST(node.right, x, right);
    }

    /*
    中序遍历: 把二叉树看成一个有序数组, 比较相邻元素大小
     */
    private long pre = Long.MIN_VALUE;

    private boolean isValidBSTMid(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!isValidBSTMid(node.left)) {
            return false;
        }
        if (node.val >= pre) {
            return false;
        }
        pre = node.val;
        return isValidBSTMid(node.right);
    }

}
