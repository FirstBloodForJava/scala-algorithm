package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_43 {

    /**
     * 98. <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/">验证二叉搜索树</a>
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        /*
        有效 二叉搜索树定义如下：
            节点的左子树只包含 严格小于 当前节点的数。
            节点的右子树只包含 严格大于 当前节点的数。
            所有左子树和右子树自身必须也是二叉搜索树。
         */
        /*
        中序遍历：左 -> 根 -> 右，中序遍历后得到的结果如果是递增的，就是二叉搜索树。
        在遍历二叉搜索树的过程中，用一个变量，记录前一个节点的值，判断当前节点的值是否 > 前一个节点值
         */
        /*
        先序遍历：额外传入当前路径的最小值和最大值，node.val 要大于最小值，且小于最大值
         */
        /*
        后续遍历：返回子树的最小值和最大值，供给父节点判断
            父节点要大于左子树的最大值，且小于右子树的最小值，才是合法的二叉搜索树
         */

        return isValidBSTPre(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTPre(TreeNode node, long min, long max) {
        if (node == null) return true;
        int x = node.val;
        return min < x && x < max
                && isValidBSTPre(node.left, min, x)
                && isValidBSTPre(node.right, x, max);
    }

}
