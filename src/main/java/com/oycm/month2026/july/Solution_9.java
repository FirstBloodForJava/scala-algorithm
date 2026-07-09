package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_9 {

    /**
     * 3532. 针对图的路径存在性查询 I
     * <br>
     * 3532. <a href="https://leetcode.cn/problems/path-existence-queries-in-a-graph-i/description/">针对图的路径存在性查询 I</a> 1659
     *
     * @param n
     * @param nums
     * @param maxDiff
     * @param queries
     * @return
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        /*
        给你一个整数 n，表示图中的节点数量，这些节点按从 0 到 n - 1 编号。
        同时给你一个长度为 n 的整数数组 nums，该数组按 非递减 顺序排序，以及一个整数 maxDiff。
        如果满足 |nums[i] - nums[j]| <= maxDiff（即 nums[i] 和 nums[j] 的 绝对差 至多为 maxDiff），则节点 i 和节点 j 之间存在一条 无向边 。
        此外，给你一个二维整数数组 queries。对于每个 queries[i] = [ui, vi]，需要判断节点 ui 和 vi 之间是否存在路径。
        返回一个布尔数组 answer，其中 answer[i] 等于 true 表示在第 i 个查询中节点 ui 和 vi 之间存在路径，否则为 false。
         */
        /*
        1 <= n == nums.length <= 1e5
        0 <= nums[i] <= 1e5
        nums 按 非递减 顺序排序。
        0 <= maxDiff <= 1e5
        1 <= queries.length <= 1e5
        queries[i] == [ui, vi]
        0 <= ui, vi < n
         */
        /*
        判断图中两点是否存在路径，可以再构建并查集后，快速判断。
        nums[i] 找可连接的边，数组有序，可以使用二分查找，
            右边界，查找满足条件 nums[j] <= nums[i] + maxDiff 的最后一个下标 j2；
            左边界，查找满足条件 nums[j] >= nums[i] - maxDiff 的第一个下标 j1；
        如果每次查找都把所有点再合并一次，最坏的情况，时间复杂度是 O(n^2)。
        对于 nums[i], j > i, nums[j] - nums[i] <= maxDiff, 由于数组非递减，nums[i+1] >= nums[i]，nums[j] - nums[i+1] <= maxDiff 肯定成立；
        在合并连通块时，i 和 [i+1, j] 边都是连通的，那么 i+1 和 [i+2, j] 就需要再连通，只需要再查找 nums[i+1] 的右边界和 i+1 连通；
        i = 1，从 1 到 n-1,考虑 nums[i] - nums[i-1] 的关系，
            如果 nums[i] - nums[i-1] <= maxDiff，那么连通它们；
            如果 nums[i] - nums[i-1] > maxDiff，那么 nums[i-1] 和任意大于 i 的下标都不会连通；
        这样遍历过去，就构建了一个并查集，不需要使用二分查找
         */
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                merge(i - 1, i);
            }
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];

        for (int i = 0; i < queries.length; i++) {
            if (isConnected(queries[i][0], queries[i][1])) {
                ans[i] = true;
            }
        }

        return ans;
    }

    int[] fa;

    public int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    public void merge(int from, int to) {
        int x = find(from);
        int y = find(to);
        fa[x] = y;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }


    public boolean[] pathExistenceQueries_1(int n, int[] nums, int maxDiff, int[][] queries) {
        /*
        题解思路：间断点 + 二分查找
        如果 nums[i+1] - nums[i] > maxDiff，那么变化 <= i 的节点，无法和 >= i+1 的节点连通，节点 i 叫做间断点。
        遍历 nums，记录所有的间断点 idx。
        对于每个 [ui, vi]，在 idx中查询第一个大于等于 ui, vi 的间断，如果两者的结果相等，那么 ui 和 vi 连通
         */
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] > maxDiff) {
                idx.add(i);
            }
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ans[i] = binarySearch(idx, q[0]) == binarySearch(idx, q[1]);
        }

        return ans;
    }

    private int binarySearch(List<Integer> idx, int target) {
        // idx 中没有重复元素，可以用库函数二分
        int i = Collections.binarySearch(idx, target);
        // 一边存在 >= 的下标，一边只存在 > target 的下标，所以区域返回真正下标值
        return i < 0 ? ~i : i;
    }

    public boolean[] pathExistenceQueries_2(int n, int[] nums, int maxDiff, int[][] queries) {
        /*
        题解思路：记录连通块所在编号
        少了并查集的合并过程
         */
        int[] id = new int[n];
        for (int i = 1; i < n; i++) {
            id[i] = id[i - 1];
            if (nums[i] - nums[i - 1] > maxDiff) {
                // 新的连通块
                id[i]++;
            }
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ans[i] = id[q[0]] == id[q[1]];
        }

        return ans;
    }

}
