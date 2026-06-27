package com.oycm.month2026.june;

import java.util.HashMap;
import java.util.Map;

public class Solution_27 {

    /**
     * 3020. <a href="https://leetcode.cn/problems/find-the-maximum-number-of-elements-in-subset/description/">子集中元素的最大数量</a> 1741
     *
     * @param nums
     * @return
     */
    public int maximumLength(int[] nums) {
        /*
        给你一个 正整数 数组 nums。
        你需要从数组中选出一个满足下述条件的子集：
            你可以将选中的元素放置在一个下标从 0 开始的数组中，并使其遵循以下模式：
            [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x]（注意，k 可以是任何 非负 的 2 的幂）。
        返回满足这些条件的子集中，元素数量的 最大值。
         */
        /*
        如果存在这样一个数组，其长度为 2(logk) + 1
        k = log(x)(m) x 最小为 2 时，m 最大为 1e9，2^k 最大为 k = 16
        1 需要特殊处理
        注意类型转换
         */
        Map<Long, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge((long) num, 1, Integer::sum);
        }
        Integer cnt1 = cnt.remove(1L);
        // (cnt - 1) | 1 奇数不变，偶数减 1
        int ans = cnt1 != null ? (cnt1 - 1) | 1 : 0;

        // 枚举每个数作为 x，求长度

        for (long x : cnt.keySet()) {
            int res = 0;
            while (cnt.getOrDefault(x, 0) >= 2) {
                x *= x;
                res += 2;
            }
            ans = Math.max(ans, res + (cnt.containsKey(x) ? 1 : -1));
        }

        return ans;
    }

    public int maximumLength_n(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge((long) num, 1, Integer::sum);
        }
        Integer cnt1 = cnt.remove(1L);
        // (cnt - 1) | 1 奇数不变，偶数减 1
        int ans = cnt1 != null ? (cnt1 - 1) | 1 : 0;

        // 枚举每个数作为 x，求长度

        for (long x : cnt.keySet()) {
            long sqrt = (long) Math.sqrt(x);
            if (sqrt * sqrt == x && cnt.getOrDefault(x, 0) >= 2) {
                continue;
            }
            int res = 0;
            while (cnt.getOrDefault(x, 0) >= 2) {
                x *= x;
                res += 2;
            }
            ans = Math.max(ans, res + (cnt.containsKey(x) ? 1 : -1));
        }

        return ans;
    }

}
