package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 108.
     * <a href="https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/">将有序数组转换为二叉搜索树</a>
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        /*
        有序数组转换成 平衡二叉搜索树 所有节点的左右子树的高度相差不超过 1
        题解思路：把数组从中间一分为二, 中间节点作为根节点, 得到两个子数组
        答案由 三部分组成：
            根节点
            根节点左侧 数组 转换成一个 平衡二叉搜索树
            根节点右侧 数组 转换成一个 平衡二叉搜索树
        原问题和子问题相似, 可以使用递归解决
        递归边界 数组 长度为 0 返回 null
         */
        return dfs(nums, 0, nums.length);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left == right) return null;
        int m = (left + right) >>> 1;
        return new TreeNode(nums[m], dfs(nums, left, m), dfs(nums, m + 1, right));
    }


}
