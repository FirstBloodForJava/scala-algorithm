package com.oycm.month2026.july;

import java.util.Arrays;

public class Solution_10 {

    /**
     * 3534. 针对图的路径存在性查询 II
     * <br>
     * 3534. <a href="https://leetcode.cn/problems/path-existence-queries-in-a-graph-ii/description/">针对图的路径存在性查询 II</a> 2507
     *
     * @param n
     * @param nums
     * @param maxDiff
     * @param queries
     * @return
     */
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        /*
        给你一个整数 n，表示图中的节点数量，这些节点按从 0 到 n - 1 编号。
        同时给你一个长度为 n 的整数数组 nums，以及一个整数 maxDiff。
        如果满足 |nums[i] - nums[j]| <= maxDiff（即 nums[i] 和 nums[j] 的 绝对差 至多为 maxDiff），则节点 i 和节点 j 之间存在一条 无向边。
        此外，给你一个二维整数数组 queries。对于每个 queries[i] = [ui, vi]，找到节点 ui 和节点 vi 之间的 最短距离 。如果两节点之间不存在路径，则返回 -1。
        返回一个数组 answer，其中 answer[i] 是第 i 个查询的结果。
         */
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        // nums[idx[i]] 是有序的
        Arrays.sort(idx, (i, j) -> nums[i] - nums[j]);
        /*
        nums = [8, 9, 2, 3, 1]
        idx  = [4, 2, 3, 0, 1]
        rank = [3, 4, 1, 2, 0]
        rank[i] 表示 nums[i] 在整个 nums 数组中在 idx 的下标（顺序）
         */
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[idx[i]] = i;
        }

        /*
        双指针：
        pa[i][0] 表示 nums 第 i 个元素往左跳的最远下标 left
         */
        int mx = 32 - Integer.numberOfLeadingZeros(n);
        int[][] pa = new int[n][mx];
        int left = 0;
        for (int i = 0; i < n; i++) {
            while (nums[idx[i]] - nums[idx[left]] > maxDiff) {
                left++;
            }
            pa[i][0] = left;
        }
        /*
        倍增？
         */
        for (int i = 0; i < mx - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = pa[p][i];
            }
        }


        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            if (q[0] == q[1]) continue;
            int l = rank[q[0]];
            int r = rank[q[1]];
            // 保证 l < r
            if (l > r) {
                int temp = l;
                l = r;
                r = temp;
            }
            int res = 0;
            for (int k = mx - 1; k >= 0; k--) {
                if (pa[r][k] > l) {
                    res |= 1 << k;
                    r = pa[r][k];
                }
            }
            ans[i] = pa[r][0] > l ? -1 : res + 1;
        }

        return ans;
    }

}
