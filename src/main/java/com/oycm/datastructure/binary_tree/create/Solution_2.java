package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 654. <a href="https://leetcode.cn/problems/maximum-binary-tree/description/">最大二叉树</a>
     *
     * @param nums
     * @return
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        /*
        递归构建二叉树:
            nums 最大值为 根节点
            递归地在 最大值 左边的子数组前缀上 构建 左子树
            递归地在 最大值 右边的子数组后缀上 构建 右子树
        递归边界：前缀/后缀为空 返回 null
        3,2,1,6,0,5
        3 [0, 3), [4,6)
        左 0 [0,0), [1, 3)
        右 5 [4,5), (6,6)
         */
        n = nums.length;
        return dfs(nums, getMaxIdx(nums, 0, n), 0, n);
    }

    static int n;

    private static TreeNode dfs(int[] nums, int max, int left, int right) {
        if (left == right) return null;
        return new TreeNode(nums[max],
                dfs(nums, getMaxIdx(nums, left, max), left, max),
                dfs(nums, getMaxIdx(nums, max + 1, right), max + 1, right));
    }

    private static int getMaxIdx(int[] nums, int left, int right) {
        if (left == right) return left;
        int max = nums[left];
        int idx = left;

        while (left < right) {
            if (max < nums[left]) {
                idx = left;
                max = nums[left];
            }
            left++;
        }

        return idx;
    }

    public static void main(String[] args) {
        constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

}
