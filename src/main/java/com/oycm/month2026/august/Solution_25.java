package com.oycm.month2026.august;

import java.util.HashSet;
import java.util.Set;

public class Solution_25 {

    /**
     * 3718. <a href="https://leetcode.cn/problems/smallest-missing-multiple-of-k/description/">缺失的最小倍数</a> 1228
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingMultiple(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k，请返回从 nums 中缺失的、最小的正整数 k 的倍数。
        倍数 指能被 k 整除的任意正整数。
         */
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int nk = k;
        while (set.contains(nk)) {
            nk += k;
        }

        return nk;
    }

}
