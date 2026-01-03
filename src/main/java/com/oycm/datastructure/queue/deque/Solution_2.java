package com.oycm.datastructure.queue.deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution_2 {

    /**
     * 2071. <a href="https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/description/">你可以安排的最多任务数目</a> 2648
     * <p>
     * 工人只能完成一个任务(使用一个药片), 且工人的力量值要 大于等于 任务的力量值要求
     *
     * @param tasks    tasks.length = n, 表示完成任务需要的力量值
     * @param workers  workers.length = m, 表示工人的力量值
     * @param pills    药片的数量, 一片可以给工人增加 strength 力量值
     * @param strength 药品的左右
     * @return 最多 有多少个任务可以被完成
     */
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        /*
        首先是可以排序
        题解思路: 二分查找答案, (0, min(n, m) + 1) 查找是否能有 k 个工人完成任务, 选择 k 个工人时, 力量值最高的 k 个工人
        枚举工人, 它能完成最简单的任务
        遍历 workers 后 k 个工人, w = workers[i], 分类讨论
            如果 w 不吃药能完成当前最简单的任务, 则选择最简单的任务完成, 因为后面更难的任务, 有更强的工人完成;
            如果 w 吃药才能完成任务, 则让他选择一个最难完成的任务, 充分利用药的价值;
            w 不吃药时, 为什么不选择吃药完成最难的任务? 如果后面有更强的工人能完成吃药才能完成的任务, 则浪费了一颗药, 不是最优解
         */
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int l = 0, r = Math.min(tasks.length, workers.length) + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(tasks, workers, pills, strength, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean check(int[] tasks, int[] workers, int pills, int strength, int k) {
        // 校验 k 个工人是否能完成任务
        // 记录吃药能完成的 最多 k 个任务
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0; // 选择的 tasks 任务
        for (int j = workers.length - k; j < workers.length; j++) {
            int w = workers[j];
            // 吃药的情况下能完成的任务
            while (i < k && tasks[i] <= w + strength) {
                q.addLast(tasks[i]);
                i++;
            }
            // 吃药的情况下, 不能完成任务
            if (q.isEmpty()) {
                return false;
            }
            // 完成最简单的任务
            if (w >= q.getFirst()) {
                q.removeFirst();
                continue;
            }
            // 药不够了
            if (pills == 0) {
                return false;
            }
            pills--;
            q.removeLast();
        }

        return true;

    }

}
