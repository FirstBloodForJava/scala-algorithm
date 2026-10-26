package com.oycm.month2026.october;

import java.util.Arrays;

public class Solution_17 {

    /**
     * 3551. <a href="https://leetcode.cn/problems/minimum-swaps-to-sort-by-digit-sum/">数位和排序需要的最小交换次数</a>
     *
     * @param nums
     * @return
     */
    public int minSwaps(int[] nums) {
        /*
        给你一个由 互不相同 的正整数组成的数组 nums，需要根据每个数字的数位和（即每一位数字相加求和）按 升序 对数组进行排序。
        如果两个数字的数位和相等，则较小的数字排在前面。
        返回将 nums 排列为上述排序顺序所需的 最小 交换次数。
        一次 交换 定义为交换数组中两个不同位置的值。
         */
        /*
        题解
         */
        int n = nums.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int x = nums[i]; x > 0; x /= 10) {
                s += x % 10;
            }
            a[i][0] = s;
            a[i][1] = nums[i];
            a[i][2] = i;
        }
        Arrays.sort(a, (p, q) -> p[0] != q[0] ? p[0] - q[0] : p[1] - q[1]);
        int cc = 0;

        for (int[] t : a) {
            int i = t[2];
            if (i < 0) {
                continue;
            }
            cc++;
            while (i >= 0) {
                int next = a[i][2];
                a[i][2] = -1;
                i = next;
            }
        }
        return n - cc;
    }

}
