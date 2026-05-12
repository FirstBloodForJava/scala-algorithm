package com.oycm.month2026.may;

import java.util.Arrays;

public class Solution_12 {

    /**
     * 1665. <a href="https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks/description/">完成所有任务的最少初始能量</a> 1901
     *
     * @param tasks 二维数组 tasks.length = n; tasks[0].length = 2
     * @return
     */
    public int minimumEffort(int[][] tasks) {
        /*
        给你一个任务数组 tasks ，其中 tasks[i] = [actual, minimum]：
            actual 是完成第 i 个任务 需要耗费 的实际能量。
            minimum 是开始第 i 个任务前需要达到的最低能量。
        如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。
        如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
        actual <= minimum
         */
        /*
        实际需要消耗的能量是 sum += tasks[i][0] i[0, n-1]
        贪心的想，如果先完成最大的最大能量要求任务 max(tasks[i][1])，初始能量为 sum + max(tasks[i][1])，后续所有剩余能量都 >= max(tasks[i][1])，后续的其它任务都能完成
        但是这个可能不是最低初始能量，[[1,2],[2,4],[4,8]] sum = 7, 7 + 8 不是最优解，8 的时候就能完成所有任务
        按 minimum 最低能量要求，从小到大排序, 最低能量要求肯定在 [sum, sum + maxMinimum] 中
            二分答案，怎么判断该能量是否能完成所有任务？
        因为 actual <= minimum，初始化 最低能量为 minMinimum，最低能量不断往
         */
        /*
        题解思路：如果 task 的某个排列是最优的（完成任务所需的初始能量最少），那么 T 中相邻任务有什么性质？
        actual 简称 a，minimum 简称 m
        设一对相邻任务 t1 = (a1, m1); t2 = (a2, m2)，设初始能量为 E0，完成这个两个任务之前的能量为 E。
        由于从一开始到完成这个两个任务之前，所消耗的能量之和是固定的 s，所以 E = E0 - s。根据该式，E 越小，初始能量 E0 也越小。
        思考，先完成哪个任务更好？ => 先完成哪个任务 E 越小。
            如果先完成 t1 再完成 t2，那么完成 t1 之前，必须满足 E >= m1；完成 t2 之前，必须满足 E - a1 >= m2。可推出 E >= max(m1, m2 + a1)
            如果先完成 t2 再完成 t1，那么完成 t2 之前，必须满足 E >= m2；完成 t1 之前，必须满足 E - a2 >= m1。可推出 E >= max(m2, m1 + a2)
        如果先完成 t1 再完成 t2 更优（或者相同），则有
            max(m1, m2 + a1) <= max(m2, m1 + a2)
        两边同时减去 a1 + a2，得
            max(m1 - a1 - a2, m2 - a2) <= max(m2 - a1 - a2, m1 - a1)
        设 d1 = m1 - a1，d2 = m2 - a2，得
            max(d1 - a2, d2) <= max(d2 - a1, d1)
        注意 1 <= actual <= minimum，d1 和 d2 都大于等于 0
            如果 d1 < d2，由于 a > 0，左侧式子等于 d2，右侧式子严格小于 d2 (a1 > 0， d2 - a1 < y; d1 < d2)
            如果 d1 >= d2，左侧式子严格小于 d1，右侧式子等于 d1，此时 max(d1 - a2, d2) <= max(d2 - a1, d1)
        所以当且仅当 d1 >= d2 成立时，max(d1 - a2, d2) <= max(d2 - a1, d1) 成立。
        所以当且仅当 m1 - a1 >= m2 - a2 成立时，先完成 t1 再完成 t2 更优（或者相同）。
         */
        // d = m - a, 从大到小排序
        Arrays.sort(tasks, (a, b) -> b[1] - b[0] - (a[1] - a[0]));
        int ans = 0;
        int s = 0;
        for (int[] task : tasks) {
            ans = Math.max(ans, s + task[1]);
            s += task[0];
        }

        /*Arrays.sort(tasks, (a, b) -> b[1] - b[0] - (a[1] - a[0]));
        int ans = 0;
        // 执行前面任务后的能量
        int e = 0;
        for (int[] task : tasks) {
            if (e < task[1]) {
                ans += task[1] - e;
                e = task[1];
            }
            e -= task[0];
        }
        return ans;*/

        return ans;
    }

    public int minimumEffort_asc(int[][] tasks) {
        /*
        设完成任务 ti = (ai, mi) 之后的能量为 e，那么完成 ti 之前的能量为 e + ai, 且需要大于等于 mi，所以完成 ti 之前的能量至少为 max(e + ai, mi)
        为了最小化初始能量，完成最后一个任务的能量应当为 0，作为 e 的初始值
        m - a 可以改成从小到大排序，就可以正序遍历数组
         */
        Arrays.sort(tasks, (a, b) -> a[1] - a[0] - (b[1] - b[0]));
        int e = 0;
        for (int[] task : tasks) {
            e = Math.max(e + task[0], task[1]);
        }
        return e;
    }


}
