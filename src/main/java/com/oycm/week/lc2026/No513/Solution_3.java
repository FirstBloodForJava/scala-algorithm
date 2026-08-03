package com.oycm.week.lc2026.No513;

public class Solution_3 {

    /**
     * 统计每个班次结束后的未完成任务数
     *
     * @param tasks
     * @param shifts
     * @return
     */
    public int[] countTasks(int[] tasks, int[] shifts) {
        /*
        给你两个整数数组 tasks 和 shifts。
            tasks[i] 表示完成第 ith 个任务所需的时间。
            shifts[j] 表示第 j 个班次可用的时间。
        任务必须按照从左到右的顺序处理。
            延续处理：如果一个任务在当前班次内没有完成，则下一班次会从该任务的相同进度位置继续处理。
            重新开始：如果一个班次内完成了所有任务，则该班次会立即结束。该班次剩余的时间会被丢弃，下一班次会重新从第 0 个任务开始。
        如果一个任务尚未被完全完成，则认为该任务是未完成的。这包括当前正在执行中的任务。
        返回一个整数数组 ans，其中 ans[j] 表示第 j 个班次结束后立即剩余的未完成任务数量。
         */
        /*
        1 <= tasks.length <= 1e5
        1 <= shifts.length <= 1e5
        1 <= tasks[i] <= 1e9
        1 <= shifts[i] <= 1e9
         */
        /*
        tasks[i] 创建一个前缀和数组，表示完成 第 i 个任务需要的时间。
        需要一个变量记录上一个班车已经完成的时间
         */
        int n = tasks.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + tasks[i];
        }
        long preTask = 0;
        int[] ans = new int[shifts.length];

        for (int i = 0; i < shifts.length; i++) {
            preTask += shifts[i];
            // 在 sums 中查找第一个大于 target 的下标
            int idx = lowerBound(sums, preTask);
            if (idx == n + 1) {
                preTask = 0;
            }
            ans[i] = n + 1 - idx;

        }

        return ans;
    }

    public int lowerBound(long[] nums, long target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


}
