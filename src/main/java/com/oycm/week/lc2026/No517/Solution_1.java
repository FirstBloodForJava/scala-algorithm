package com.oycm.week.lc2026.No517;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 统计特殊整数个数
     *
     * @param nums
     * @return
     */
    public int countSpecialIntegers(int[] nums) {
        /*
        给你一个整数数组 nums。
        如果整数 x 在 nums 中的所有出现位置都位于同一个 连续 区间内，则称 x 为 特殊整数。
        返回 nums 中 不同 特殊整数的数量。
         */
        /*
        x 只在 [i, j] 出现
         */
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && nums[j] == nums[j - 1]) {
                j++;
            }
            map.merge(nums[i], 1, Integer::sum);
            i = j;
        }
        for (Integer x : map.values()) {
            if (x == 1) {
                ans++;
            }
        }
        return ans;
    }

}
