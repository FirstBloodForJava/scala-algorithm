package com.oycm.week.lc2026.No517;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 构造子集和的最少操作次数 I
     *
     * @param nums
     * @param sum
     * @return
     */
    public int minOperations(int[] nums, int sum) {
        /*
        给你一个整数数组 nums 和一个整数 sum。
        一次 操作 中，选择一个当前值为 x 的元素，并将其替换为 2 * x 或 floor(x / 2)。
        对于每个元素，对其执行的所有 乘法 操作都必须发生在任何 除法 操作之前。
        返回所需的 最少 操作次数，使得操作后的数组中存在一个 子集，其元素之和 恰好 等于 sum。如果无法做到，则返回 -1。
        数组的 子集 是从数组中选择若干个元素得到的集合，也可以不选择任何元素。
        floor() 函数返回除法结果的整数部分。
         */
        /*
        对于每个元素，对其执行的所有 乘法 操作都必须发生在任何 除法 操作之前。
        x 进行除法操作后，就不能进行乘法操作
        0-1 背包
         */

        int[] f = new int[sum + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;

        for (int x : nums) {
            int[] ft = f.clone();
            // 只进行除法
            for (int v = x, cost = 0; v > 0; v /= 2, cost++) {
                if (v > sum) {
                    continue;
                }
                for (int i = v; i <= sum; i++) {
                    ft[i] = Math.min(ft[i], f[i - v] + cost);
                }
            }
            // 只进行乘法操作
            for (int v = x, cost = 0; v <= sum; v *= 2, cost++) {
                for (int i = v; i <= sum; i++) {
                    ft[i] = Math.min(ft[i], f[i - v] + cost);
                }
            }
            f = ft;
        }
        if (f[sum] == Integer.MAX_VALUE / 2) {
            return -1;
        }
        return f[sum];
    }

}
