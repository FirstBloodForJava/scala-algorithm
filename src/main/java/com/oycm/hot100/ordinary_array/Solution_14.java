package com.oycm.hot100.ordinary_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution_14 {

    /**
     * 56. <a href="https://leetcode.cn/problems/merge-intervals/description/">合并区间</a>
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        /*
        以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start, end] 。
        请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
         */
        /*
        把 intervals 数组按 intervals[i][0] 升序排序
        end = intervals[0][1], 如果 intervals[i][0] <= end，更新 end = max(end, intervals[i][1]) i++
         */
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (i + 1 < n && intervals[i + 1][0] <= end) {
                end = Math.max(end, intervals[i + 1][1]);
                i++;
            }
            ans.add(new int[]{start, end});
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] merge_diff(int[][] intervals) {
        /*
        差分数组：对 [intervals[i][0], intervals[i][1]] 区间元素增加 1，差分数组还原后，如果 d[i] > 0，则说明区间是连通的。
        [1,2] [3,4] 会出现误判情况，对 intervals[i][] * 2 区间 +1，这样区间就被拆分了
        [2,4] [6, 8]
         */
        int mx = 0;
        for (int[] p : intervals) {
            mx = Math.max(mx, p[1]);
        }
        int[] d = new int[2 * mx + 2];
        for (int[] p : intervals) {
            d[2 * p[0]]++;
            d[2 * p[1] + 1]--;
        }
        List<int[]> ans = new ArrayList<>();
        int sum = 0;
        int start = -1;
        for (int i = 0; i < d.length; i++) {
            sum += d[i];
            if (sum > 0) {
                if (start < 0) {
                    start = i;
                }
            } else if (start >= 0) {
                ans.add(new int[]{start / 2, i / 2});
                start = -1;

            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

}
