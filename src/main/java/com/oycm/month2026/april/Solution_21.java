package com.oycm.month2026.april;

import java.util.*;

public class Solution_21 {

    /**
     * 1722. <a href="https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations/description/">执行交换操作后的最小汉明距离</a>
     * <p>
     * 两个整数数组 source 和 target ，长度都是 n; n [1, 1e5]
     *
     * @param source
     * @param target
     * @param allowedSwaps allowedSwaps[i] = [a, b] 表示可以交换 source 数组，下标 a, b 对应的值。
     * @return 对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        /*
        相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。满足 source[i] != target[i] 的下标 i（0 <= i <= n-1）的数量
         */
        /*
        题解思路：allowedSwaps[i][0], allowedSwaps[i][1] 连边，可以得到一个无向图，一个无向图中的元素可以随意交换位置
        只要看一个无向图中 source 和 target 的元素情况，用一个 hash 表记录无向图中数字出现的次数，如果在 source 中出现，次数 +1，在 target 中出现，次数 -1；
        这样只要次数为 0，表示 source[i] == source[j]；两者的绝对值和 / 2 就是最小汉名距离

         */
        int n = source.length;
        int ans = 0;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] row : allowedSwaps) {
            int x = row[0], y = row[1];
            g[x].add(y);
            g[y].add(x);
        }
        boolean[] vis = new boolean[n];

        for (int cur = 0; cur < n; cur++) {
            if (!vis[cur]) {
                Map<Integer, Integer> diff = new HashMap<>();
                dfs(cur, source, target, g, vis, diff);
                for (Integer value : diff.values()) {
                    ans += Math.abs(value);
                }
            }
        }

        return ans / 2;
    }

    public void dfs(int cur, int[] source, int[] target, List<Integer>[] g, boolean[] vis, Map<Integer, Integer> diff) {
        vis[cur] = true;
        diff.merge(source[cur], 1, Integer::sum);
        diff.merge(target[cur], -1, Integer::sum);
        for (Integer next : g[cur]) {
            if (!vis[next]) {
                dfs(next, source, target, g, vis, diff);
            }
        }
    }

}
