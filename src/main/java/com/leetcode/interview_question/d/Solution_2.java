package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 面试题 04.02. <a href="https://leetcode.cn/problems/minimum-height-tree-lcci/description/">最小高度树</a>
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        /*
        给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
         */
        /*
        构建平衡二叉树
         */
        return dfs(nums, 0, nums.length);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int m = (left + right) >>> 1;
        return new TreeNode(nums[m], dfs(nums, left, m), dfs(nums, m + 1, right));
    }

}
