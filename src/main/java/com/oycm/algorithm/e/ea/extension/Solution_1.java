package com.oycm.algorithm.e.ea.extension;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 454. <a href="https://leetcode.cn/problems/4sum-ii/description/">四数相加 II </a>
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return 求 四个数组中 nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 的元组数
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
        题解思路: nums1[i] + nums2[j] == - (nums3[k] + nums4[l]) 的方案数
         */
        int ans = 0;
        Map<Integer, Integer> s1 = new HashMap<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s1.merge(nums1[i] + nums2[j], 1, Integer::sum);
            }
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                ans += s1.getOrDefault(-nums3[k] - nums4[l], 0);
            }
        }

        return ans;
    }
}
