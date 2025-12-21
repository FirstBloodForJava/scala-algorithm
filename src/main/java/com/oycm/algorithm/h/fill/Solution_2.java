package com.oycm.algorithm.h.fill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    /**
     * 2935. <a href="https://leetcode.cn/problems/maximum-strong-pair-xor-ii/description/">找出强数对的最大异或值 II</a> 2349
     * <p>
     * 强数对: |x - y| <= min(x, y), x 和 y 可以是同一个数
     *
     * @param nums 正整数数组
     * @return 求 数组 nums 所有可能强对数的 异或最大值
     */
    public int maximumStrongPairXor(int[] nums) {
        /*
        假设 x 是较小数, 则 y 的范围 y - x <= x, x <= y <= 2x, 所有可以对数组进行排序处理

         */
        Arrays.sort(nums);
        int ans = 0, n = nums.length, fill = 0;
        int k = 31 - Integer.numberOfLeadingZeros(nums[n - 1]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = k; i >= 0; i--) {
            map.clear();
            fill |= 1 << i;
            int newValue = ans | (1 << i);
            for (int num : nums) {
                int key = num & fill;
                // x <= y 的, num & fill 相同时, x 会不断变大, 如果还不符合要求, 则前面的数更不符合要求
                if (map.containsKey(key ^ newValue) && map.get(key ^ newValue) * 2 >= num) {
                    ans = newValue;
                    break;
                }
                map.put(key, num);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(500 ^ 520);
    }

}
