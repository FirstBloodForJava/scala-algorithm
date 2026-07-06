package com.oycm.month2026.july;

import java.util.Arrays;

public class Solution_6 {

    /**
     * 1288. 删除被覆盖区间
     * <br>
     * 1288. <a href="https://leetcode.cn/problems/remove-covered-intervals/description/">删除被覆盖区间</a> 1375
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        /*
        给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
        只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
        在完成所有删除操作后，请你返回列表中剩余区间的数目。
         */
        /*
        intervals 按 intervals[0] 升序排序，如果 intervals[0] 相等，则按 intervals[1] 降序排序
        如果 intervals[i+1] 没有被 intervals[i] 覆盖，排序后 intervals[i][0] < intervals[i+1][0]，只能是 intervals[i][1] < intervals[i+1][1]；
        intervals[i+1] 区间右端点更大，如果后面的区间能被 intervals[i] 区间合并，一定能被区间 intervals[i+1] 合并
         */
        int n = intervals.length;
        int ans = n;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && intervals[j][1] <= intervals[i][1]) {
                j++;
                ans--;
            }
            i = j;
        }

        return ans;
    }

}
