package com.oycm.hot100.struct;

public class Solution_102 {

    /**
     * 621. <a href="https://leetcode.cn/problems/task-scheduler/description/">任务调度器</a>
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        /*
        给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，以及一个冷却时间 n。
        每个周期或时间间隔允许完成一项任务。任务可以按任何顺序完成，但有一个限制：两个 相同种类 的任务之间必须有长度为 n 的冷却时间。
        返回完成所有任务所需要的 最短时间间隔。
         */
        /*
        1 <= tasks.length <= 1e4; 0 <= n <= 100
        tasks[i] 是大写英文字母
         */
        /*
        如果 n > 0, 设出现次数最多的任务为 mx 次，最短时间至少为 max((mx-1)*(n+1), tasks.length )
        构造一个 mx 行，n+1 列的矩阵，把 mx 放在第一列，后续有和 mx 此次相同的，则依次放在其后面，
            如果后续的矩阵列没有全部占用，则 task < max((mx-1)*(n+1) + mxCnt
            如果后续的矩阵全部占用了，没有空闲时间浪费，至少需要 task 时间
         */
        if (n == 0) return tasks.length;
        int[] cnt = new int[26];
        for (char c : tasks) {
            cnt[c - 'A']++;
        }
        int mx = 0;
        for (int x : cnt) mx = Math.max(x, mx);
        int mxCnt = 0;
        for (int x : cnt) mxCnt += x == mx ? 1 : 0;

        return Math.max(tasks.length, (mx - 1) * (n + 1) + mxCnt);
    }

}
