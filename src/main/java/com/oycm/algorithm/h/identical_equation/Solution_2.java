package com.oycm.algorithm.h.identical_equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_2 {

    /**
     * 2354. <a href="https://leetcode.cn/problems/number-of-excellent-pairs/description/">优质数对的数目</a> 2076
     * <p>
     * (num1, num2) 优质数对定义:
     * num1 和 num2 都 在数组 nums 中存在
     * num1 | num2 和 num1 & num2 的 二进制中值为 1 的位数之和大于等于 k
     *
     * @param nums
     * @param k
     * @return 求不同优质数对的数目 (1,2), (2,1) 算不同, (1,1) 1 在 nums 中出现至少一次, 算一个
     */
    public long countExcellentPairs(int[] nums, int k) {
        /*
        cnt(a | b) + cnt(a & b) = cnt(a) + cnt(b)
        题解-计算方案数: cnt(i) 表示 i 中 1 出现的次数
            去重 nums, 记录 cnt(nums[i]) 出现的次数, 写一个二重循环遍历 map k1 + k2 >= k ans += v1 * v2
         */
        long ans = 0;
        Set<Integer> set = new HashSet<>();
        Map<Integer, Long> map = new HashMap<>();
        for (int num : nums) {
            if (set.add(num)) {
                map.merge(Integer.bitCount(num), 1L, Long::sum);
            }
        }
        for (Map.Entry<Integer, Long> kv1 : map.entrySet()) {
            for (Map.Entry<Integer, Long> kv2 : map.entrySet()) {
                if (kv1.getKey() + kv2.getKey() >= k) {
                    ans += kv1.getValue() * kv2.getValue();
                }
            }
        }

        return ans;
    }

    public long optimize(int[] nums, int k) {
        /*
        用数组前缀和/后缀和优化 二重循环计算
         */
        long ans = 0;
        int size = 30;
        Set<Integer> set = new HashSet<>();
        long[] cnt = new long[size];
        for (int num : nums) {
            if (set.add(num)) {
                cnt[Integer.bitCount(num)]++;
            }
        }
        long s = 0;
        for (int i = k; i < size; i++) {
            s += cnt[i];
        }
        for (int y = 0; y < size; y++) {
            ans += s * cnt[y];
            // 计算下一轮数量 x + 1 + y - k >= 0
            // x = k - y - 1 >= 0, x < size
            int x = k - 1 - y;
            if (x >= 0 && x < size) {
                s += cnt[x];
            }
        }

        return ans;
    }

}
