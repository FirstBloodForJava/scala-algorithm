package com.oycm.month2026.june;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution_26 {

    /**
     * 3739. <a href="https://leetcode.cn/problems/count-subarrays-with-majority-element-ii/description/">统计主要元素子数组数目 II</a> 2090
     *
     * @param nums
     * @param target
     * @return
     */
    public long countMajoritySubarrays(int[] nums, int target) {
        /*
        给你一个整数数组 nums 和一个整数 target。
        返回数组 nums 中满足 target 是 主要元素 的 子数组 的数目。
        一个子数组的 主要元素 是指该元素在该子数组中出现的次数 严格大于 其长度的 一半 。
        子数组 是数组中的一段连续且 非空 的元素序列。
         */
        /*
        把 nums[i] 中 == target 的元素看作 1，其它的看作 -1，问题转换成了求 子数组和 > 0 的子数组数目
        这样计算超时
         */
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        long ans = 0;
        cnt.put(0, 1);
        int sum = 0;
        for (int x : nums) {
            if (x == target) {
                sum++;
            } else {
                sum -= 1;
            }
            // 查找小于 sum 的所有 key
            for (Integer value : cnt.headMap(sum).values()) {
                ans += value;
            }
            cnt.merge(sum, 1, Integer::sum);
        }

        return ans;
    }

    public long countMajoritySubarrays_optimize(int[] nums, int target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int s = 0;
        int f = 0;
        for (int x : nums) {
            if (x == target) {
                f += cnt.getOrDefault(s, 0);
                s++;
            } else {
                s--;
                f -= cnt.getOrDefault(s, 0);
            }
            ans += f;
            cnt.merge(s, 1, Integer::sum);
        }
        return ans;
    }

}
