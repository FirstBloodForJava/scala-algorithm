package com.oycm.week.lc2026.No517;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_4 {

    /**
     * 构造子集和的最少操作次数 II
     *
     * @param nums
     * @param sum
     * @return
     */
    public static int minOperations(int[] nums, int sum) {
        /*
        给你一个整数数组 nums 和一个整数 sum。
        一次 操作 中，选择一个当前值为 x 的元素，并将其替换为 2 * x 或 floor(x / 2)。
        对于每个元素，乘法 操作和 除法 操作可以按照任意顺序执行。
        返回所需的 最少 操作次数，使得操作后的数组中存在一个 子集，其元素之和 恰好 等于 sum。如果无法做到，则返回 -1。
        数组的子集是从数组中选择若干个元素得到的集合，也可以不选择任何元素。
        floor() 函数返回除法结果的整数部分。
         */
        /*
        13 1101
        乘法执行之后，相当于二进制后面补 0，后续再执行除法是多余的
        执行 1 次除法：110(6) 得到一个新数，可再次进行乘法
        执行 2 次除法：11(3) 得分一个新数，可再次进行乘法，比第二次多了操作，不如，第一次更优，可以不考虑
        执行 3 次除法：1(1) 得到一个新数，2 的幂
         */
        int[] f = new int[sum + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;

        for (int x : nums) {
            int[] ft = f.clone();
            Set<Integer> set = new HashSet<>();
            // 先只进行乘法操作
            for (int v = x, cost = 0; v <= sum; v *= 2, cost++) {
                set.add(v);
                for (int i = v; i <= sum; i++) {
                    ft[i] = Math.min(ft[i], f[i - v] + cost);
                }
            }
            // 只进行除法
            for (int v = x, cost = 0; v > 0; v /= 2, cost++) {
                if (v > sum) {
                    continue;
                }
                for (int i = v; i <= sum; i++) {
                    ft[i] = Math.min(ft[i], f[i - v] + cost);
                }
                // 执行乘法操作
                for (int kv = v, kc = cost; kv <= sum; kv *= 2, kc++) {
                    // 前面操作更少
                    if (!set.add(kv)) {
                        break;
                    }
                    for (int i = kv; i <= sum; i++) {
                        ft[i] = Math.min(ft[i], f[i - kv] + kc);
                    }
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
