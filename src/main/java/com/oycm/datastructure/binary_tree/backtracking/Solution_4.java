package com.oycm.datastructure.binary_tree.backtracking;

import com.oycm.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution_4 {


    /**
     * 437. <a href="https://leetcode.cn/problems/path-sum-iii/description/">路径总和 III</a>
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long,Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        dfs(root, 0L, targetSum, cnt);
        return ans;
    }

    int ans = 0;

    private void dfs(TreeNode node, long s, int targetSum, Map<Long, Integer> cnt) {
        if (node == null) {
            return;
        }

        s += node.val;
        // 把 node 当作路径的终点，统计有多少个起点
        ans += cnt.getOrDefault(s - targetSum, 0);

        // 计数
        cnt.merge(s, 1, Integer::sum);
        dfs(node.left, s, targetSum, cnt);
        dfs(node.right, s, targetSum, cnt);
        // 回溯
        cnt.merge(s, -1, Integer::sum);
    }

}
