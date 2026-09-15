package com.oycm.month2026.september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_12 {

    /**
     * 3414. <a href="https://leetcode.cn/problems/maximum-score-of-non-overlapping-intervals/description/">不重叠区间的最大得分</a> 2723
     *
     * @param intervals
     * @return
     */
    public int[] maximumWeight(List<List<Integer>> intervals) {
        /*
        给你一个二维整数数组 intervals，其中 intervals[i] = [li, ri, weighti]。区间 i 的起点为 li，终点为 ri，权重为 weighti。
        你最多可以选择 4 个互不重叠 的区间。所选择区间的 得分 定义为这些区间权重的总和。
        返回一个至多包含 4 个下标且 字典序最小 的数组，表示从 intervals 中选中的互不重叠且得分最大的区间。
        如果两个区间没有任何重叠点，则称二者 互不重叠 。特别地，如果两个区间共享左边界或右边界，也认为二者重叠。
         */
        /*
        比 1235 题多了一维，dp 答案逻辑既比较得分，又比较字典序
         */
        int n = intervals.size();
        int[][] g = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            g[i] = new int[]{interval.get(0), interval.get(1), interval.get(2), i};
        }
        Arrays.sort(g, (a, b) -> a[1] - b[1]);
        Pair[][] f = new Pair[n + 1][5];
        Arrays.setAll(f[0], i -> new Pair(0, new ArrayList<>()));
        for (int i = 0; i < n; i++) {
            int k = search(g, i, g[i][0]);
            f[i + 1][0] = new Pair(0, new ArrayList<>());
            for (int j = 1; j < 5; j++) {
                // 不选区间得分
                long s1 = f[i][j].sum;
                // 选这个区间得分
                long s2 = f[k + 1][j - 1].sum + g[i][2];
                if (s1 > s2) {
                    f[i + 1][j] = f[i][j];
                    continue;
                }
                // 新选的下标
                List<Integer> newId = new ArrayList<>(f[k + 1][j - 1].id);
                newId.add(g[i][3]);
                Collections.sort(newId);
                if (s1 == s2 && compareList(newId, f[i][j].id) > 0) {
                    newId = f[i][j].id;
                }
                f[i + 1][j] = new Pair(s2, newId);
            }
        }

        return f[n][4].id.stream().mapToInt(v -> v).toArray();
    }

    private record Pair(long sum, List<Integer> id) {
    }

    public int search(int[][] g, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (g[mid][1] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int compareList(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        return a.size() - b.size();
    }

}
