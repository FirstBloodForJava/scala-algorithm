package com.oycm.datastructure.segment_tree.binary;

public class Solution_2 {


    /**
     * 2940. <a href="https://leetcode.cn/problems/find-building-where-alice-and-bob-can-meet/description/">找到 Alice 和 Bob 可以相遇的建筑</a> 2327
     *
     * @param heights
     * @param queries
     * @return
     */
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        /*
        给你一个下标从 0 开始的正整数数组 heights ，其中 heights[i] 表示第 i 栋建筑的高度。
        如果一个人在建筑 i ，且存在 i < j 的建筑 j 满足 heights[i] < heights[j] ，那么这个人可以移动到建筑 j 。
        给你另外一个数组 queries ，其中 queries[i] = [ai, bi] 。第 i 个查询中，Alice 在建筑 ai ，Bob 在建筑 bi 。
        请你能返回一个数组 ans ，其中 ans[i] 是第 i 个查询中，Alice 和 Bob 可以相遇的 最左边的建筑 。如果对于查询 i ，Alice 和 Bob 不能相遇，令 ans[i] 为 -1 。
         */
        /*
        找最左边相遇的建筑，可以分类讨论：
            如果 q[0] = q[1] 已经相遇，答案为 q[1]
            如果 heights[q[1]] > height[q[0]] 且 q[1] > q[0]， Bob 不动，Alice 移动到 Bob 处，答案为 q[1]；
            如果 heights[q[0]] > height[q[1]] 且 q[0] > q[1]， Alice 不动，Bob 移动到 Alice 处，答案为 q[1]；
            其它情况，在 (max(ai, bi), n) 区间查找 第一个大于 max(ha, hb) 的下标
        ai 记为 a，bi 记为 b，不妨设 a <= b，上面的情况就分为两种了
            a == b || ha < hb，答案是 b
            其它情况，怎么在 (b, n) 区间查找第一个大于 max(ha, hb) 的下标
        heights 数组不是有序，怎么能加快查找？
        题解思路：创建一个维护区间最大值 mx 的线段树。
         */
        int n = heights.length;
        mx = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        // 构建线段树
        build(1, 0, n - 1, heights);

        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                ans[i] = query(1, 0, n - 1, b + 1, heights[a]);
            }
        }

        return ans;
    }

    private int[] mx;

    private void build(int o, int l, int r, int[] heights) {
        if (l == r) {
            mx[o] = heights[l];
            return;
        }
        int m = (l + r) / 2;
        build(o * 2, l, m, heights);
        build(o * 2 + 1, m + 1, r, heights);
        mx[o] = Math.max(mx[o * 2], mx[o * 2 + 1]);
    }

    private int query(int o, int l, int r, int L, int v) {
        if (mx[o] <= v) {
            // 区间最大值 <= v
            return -1;
        }
        if (l == r) {
            return l;
        }
        int m = (l + r) / 2;
        if (L <= m) {
            // 先查左子树
            int pos = query(o * 2, l, m, L, v);
            if (pos >= 0) {
                return pos;
            }
        }
        // 左子树未找到，再查右子树
        return query(o * 2 + 1, m + 1, r, L, v);
    }

}
