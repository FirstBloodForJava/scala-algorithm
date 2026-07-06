package com.oycm.week.lc2026.No509;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_3 {

    /**
     * 3984. 可整除游戏
     * <br>
     * 3984. <a href="https://leetcode.cn/problems/divisible-game/description/">可整除游戏</a>
     *
     * @param nums
     * @return
     */
    public int divisibleGame(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums。
        Alice 和 Bob 正在玩一个游戏。Alice 会选择：
            一个整数 k，满足 k > 1。
            两个整数 l 和 r，满足 0 <= l <= r < n。
        初始时，Alice 和 Bob 的分数都为 0。
        对于区间 [l, r]（包含两端）中的每个下标 i：
            如果 nums[i] 能被 k 整除，则 Alice 的分数 增加 nums[i]。
            否则，Bob 的分数 增加 nums[i]。
        分数差 定义为 Alice 的分数 减去 Bob 的分数。
        Alice 希望 最大化 分数差。如果有多个 k 可以达到 最大 分数差，她会选择其中 最小 的 k。
        返回 最大 分数差与所选 k 的 乘积 。由于结果可能很大，请返回其对 109 + 7 取余数后的结果。
         */
        /*
        和 质因子 相关。
         */
        int mod = 1000000007;
        List<Integer> allPrimeDivisors = new ArrayList<>();
        for (int x : nums) {
            for (int p = 2; p * p <= x; p++) {
                // 第一个能整除的数，一定是最小的质因子
                if (x % p == 0) {
                    allPrimeDivisors.add(p);
                    do {
                        x /= p;
                    } while (x % p == 0);
                }
            }
            if (x > 1) {
                allPrimeDivisors.add(x);
            }
        }
        if (allPrimeDivisors.isEmpty()) {
            return mod - 2;
        }

        Collections.sort(allPrimeDivisors);
        // 从小到大枚举质因子
        int maxDiff = Integer.MIN_VALUE;
        int bestK = 0;
        int preK = 0;
        for (int k : allPrimeDivisors) {
            if (k == preK) continue;
            int diff = maxSubArray(nums, k);
            if (diff > maxDiff) {
                maxDiff = diff;
                bestK = k;
            }
            preK = k;
        }
        return (int) ((long)maxDiff * bestK % mod);
    }

    private int maxSubArray(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        // f 表示到 nums[i] 结尾的最大子数组和
        int f = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + (x % k == 0 ? x : -x);
            ans = Math.max(f, ans);
        }
        return ans;
    }

}
