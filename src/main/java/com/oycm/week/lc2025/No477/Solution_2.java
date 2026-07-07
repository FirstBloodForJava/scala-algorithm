package com.oycm.week.lc2025.No477;

import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    /**
     * 3755. 最大平衡异或子数组的长度
     * <br>
     * 3755. <a href="https://leetcode.cn/problems/find-maximum-balanced-xor-subarray-length/description/">最大平衡异或子数组的长度</a> 1663
     *
     * @param nums
     * @return
     */
    public int maxBalancedSubarray(int[] nums) {
        /*
        给你一个整数数组 nums，返回同时满足以下两个条件的 最长子数组的长度：
            子数组的按位异或（XOR）为 0。
            子数组包含的 偶数 和 奇数 数量相等。
        如果不存在这样的子数组，则返回 0。
        子数组 是数组中的一个连续、非空 元素序列。
         */
        /*
        奇数和偶数 按位异或 肯定是非 0。子数组所有奇数 按位异或 结果为偶数。符合要求的子数组，奇数 数量为偶数。
        子数组 按位异或 为 0，可以使用前缀异或和 + hash 表，可以快速判断是否存在 按位异或为 0 的子数组。
        怎么快速判断 偶数 和 奇数 数量是否相等？
        相同的异或和，记录 下标，以及前缀 奇数数量，判断当前 r-l+1 == 2*odds 是否成立，这样还要再次枚举该集合才能判断是否存在合法子数组。
        hash 表记录首次出现 xor 以及 diff = 偶数数量-奇数数量 下标左端点，用当前的 (xor, diff) 查询是否存在最左边的下标。
         */
        Map<Pair, Integer> map = new HashMap<>();
        int ans = 0;
        int xor = 0;
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(new Pair(xor, diff), i);
            xor ^= nums[i];
            diff += nums[i] % 2 == 0 ? 1 : -1;
            int l = map.getOrDefault(new Pair(xor, diff), i + 1);
            ans = Math.max(ans, i - l + 1);
        }

        return ans;
    }

    private record Pair(int xor, long diff) {
    }

}
