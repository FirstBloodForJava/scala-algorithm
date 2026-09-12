package com.oycm.week.lc2019.No159;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 1235. <a href="https://leetcode.cn/problems/maximum-profit-in-job-scheduling/description/">规划兼职工作</a> 2023
     *
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        /*
        你打算利用空闲时间来做兼职工作赚些零花钱。
        这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
        给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
        注意，时间上出现重叠的 2 份工作不能同时进行。
        如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
         */
        /*
        提示 1：按照工作结束时间排序
        排序后，定义 f[i] 表示第 i 个任务结束时间获得的最大报酬
        f[i] = max(f[i-1], f[j] + jobs[i])：第 i 个任务不做最大报酬，第 i 个任务开始前获得的最大报酬（结束时间有序，可二分查找）
         */
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        int[] f = new int[n + 1];

        for (int i = 0; i < n; i++) {
            // 返回 任务结束时间 小于等于 当前任务开始时间的最大下标
            int j = search(jobs, i, jobs[i][0]);
            f[i + 1] = Math.max(f[i], f[j + 1] + jobs[i][2]);
        }

        return f[n];
    }

    public int search(int[][] jobs, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (jobs[mid][1] <= target) {
                left = mid;
            } else {
                right = mid;
            }

        }
        return left;
    }
}
