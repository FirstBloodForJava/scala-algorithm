package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_42 {

    /**
     * 108. <a href="https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/">将有序数组转换为二叉搜索树</a>
     *
     * @param nums nums.length [1, 1e4]
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        /*
        数组区间 [l, r)，中间节点作为二叉搜索数的根节点
            [l, m) 为左子树
            [m+1, r) 为右子树
         */
        return dfs(nums, 0, nums.length);
    }

    public TreeNode dfs(int[] nums, int l, int r) {
        if (l == r) return null;
        int m = (l + r) >>> 1;
        return new TreeNode(nums[m], dfs(nums, l, m), dfs(nums, m + 1, r));
    }

}
