package com.oycm.week.lc2026.No504;

import java.util.*;

public class Solution_4 {

    public int[] maximumMEX(int[] nums) {
        /*
        给你一个整数数组 nums。
        你需要构造一个数组 result，具体做法是重复执行以下操作，直到 nums 变为空：
            选择一个整数 k，满足 1 <= k <= len(nums)。
            计算 nums 的前 k 个元素的 MEX。
            将这个 MEX 附加到 result。
            从 nums 中移除前 k 个元素。
        返回执行这些操作后能得到的字典序最大的数组 result。
        数组的 MEX 是指数组中不包含的最小非负整数。
        如果两个数组 a 和 b 在第一个不同的下标处，数组 a 的元素大于数组 b 的对应元素，
        则数组 a 字典序大于数组 b。如果前 min(a.length, b.length) 个元素都相同，那么较长的数组是字典序更大的数组。
         */
        /*
        要想字典序大，第一个 MEX 就要大。若 MEX = m，m > 0，
            则一定存在一个子数组包含 [0, m-1] 所有值，
         */
        /*
        优化点一：mex 最大为 n，大于等于 n 的数不需要考虑
         */
        int n = nums.length;
        ArrayDeque<Integer>[] q = new ArrayDeque[n + 1];
        Arrays.setAll(q, l -> new ArrayDeque<>());
        for (int i = 0; i < n; i++) {
            // 记录相同值得所有下标
            if (nums[i] < n) {
                q[nums[i]].add(i);
            }
        }
        // 不包含最小值 0，每次删除一个元素，所有 MEX = 0
        if (q[0].isEmpty()) {
            return new int[n];
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int start = i;
            int mex = 0;
            for (; ; mex++) {
                while (!q[mex].isEmpty() && q[mex].peekFirst() < start) {
                    q[mex].pollFirst();
                }
                if (q[mex].isEmpty()) {
                    break;
                }
                i = Math.max(i, q[mex].peekFirst());
            }

            nums[idx++] = mex;
        }

        return Arrays.copyOf(nums, idx);
    }
}
