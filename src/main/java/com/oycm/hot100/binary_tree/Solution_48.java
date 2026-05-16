package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_48 {

    /**
     * 437. <a href="https://leetcode.cn/problems/path-sum-iii/description/">路径总和 III</a>
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        /*
        本质和求连续子数组和为 k 一样，可以使用枚举右端点，记录左端点和的数量
        这里就是枚举二叉树路径的终点，查看前面是否有等于 s - target 的起始点
        cnt 的数量需要回溯
         */
        return ans;
    }

    int ans = 0;

}
