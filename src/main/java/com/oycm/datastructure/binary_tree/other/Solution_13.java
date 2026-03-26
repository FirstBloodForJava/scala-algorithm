package com.oycm.datastructure.binary_tree.other;

public class Solution_13 {

    /**
     * 2673. <a href="https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/description/">使二叉树所有路径值相等的最小代价</a> 1917
     *
     * @param n
     * @param cost
     * @return
     */
    public int minIncrements(int n, int[] cost) {
        /*
        题解思路: 找到两个互为兄弟的叶子节点, 这两个节点除了叶子节点不一样, 前面的路径都一样, 所以 要增加的值就是 两个叶子节点的绝对值差
        这样之后，两个叶子节点的父节点就变成了新的叶子节点 node + max(l, r) 遍历完所有的叶子节点，上一层就变成了新的叶子节点
         */
        int ans = 0;
        for (int i = n / 2; i > 0; i--) {
            ans += Math.abs(cost[i * 2 - 1] - cost[i * 2]);
            cost[i - 1] += Math.max(cost[i * 2 - 1], cost[i * 2]);
        }

        return ans;
    }

}
