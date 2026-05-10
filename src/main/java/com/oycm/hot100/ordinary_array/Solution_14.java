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

}
