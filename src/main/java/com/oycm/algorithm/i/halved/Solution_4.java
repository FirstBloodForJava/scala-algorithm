package com.oycm.algorithm.i.halved;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_4 {

    /**
     * 1755. <a href="https://leetcode.cn/problems/closest-subsequence-sum/description/">最接近目标值的子序列和</a> 2364
     *
     * @param nums 整数数组, nums.length [1, 40]; nums[i] [-1e7, 1e7]
     * @param goal [-1e9, 1e9]
     * @return
     */
    public int minAbsDifference(int[] nums, int goal) {
        /*
        从 nums 中选出一个子序列，使子序列元素总和最接近 goal, 最小化绝对差 abs(sum - goal)
         */
        /*
        goal == 0， 最小值为 0
        不为 0 时，可对元素分两组处理，一组先枚举所有的可能 list，再排序
        另一组枚举，在 list 中查找最小可能
         */
        if (goal == 0) return 0;
        int ans = Math.abs(goal);
        int n = nums.length, m = n / 2;
        List<Long> l = new ArrayList<>();
        // 二进制枚举所有组合
        for (int i = 1; i < (1 << m); i++) {
            long total = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    total += nums[j];
                }
            }
            l.add(total);
        }
        Collections.sort(l);

        for (int i = 0; i < (1 << (n - m)); i++) {
            long total = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    total += nums[j];
                }
            }
            // goal = l(?) + x, 查找 l 中离 goal - x 最近的点
            int idx = Collections.binarySearch(l, goal - total);
            if (idx < 0) {
                idx = ~idx;
            }
            if (idx < l.size()) {
                ans = (int) Math.min(ans, Math.abs(l.get(idx) + total - goal));
            }
            if (idx > 0) {
                ans = (int) Math.min(ans, Math.abs(l.get(idx - 1) + total - goal));
            }

            if (ans == 0) return ans;

        }

        return ans;

    }

}
