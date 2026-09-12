package com.oycm.dualweek.lc2026.No191;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_1 {

    /**
     * 统计等间距出现整数数目 I
     *
     * @param nums
     * @return
     */
    public int countSpecialIntegers(int[] nums) {
        /*
        给你一个整数数组 nums。
        如果一个整数 x 满足以下条件，则被称为 特别 的：
            x 在 nums 中 恰好出现三次。
            x 的 所有 三次出现，在 nums 中都是 等间隔 的。换句话说，如果 x 的所有出现位置的下标为 i1 < i2 < i3，那么 i2 - i1 = i3 - i2。
        返回 nums 中 不同 特别整数的数量。
         */
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.computeIfAbsent(nums[i], k -> new ArrayList<>());
            list.add(i);
        }
        int ans = 0;
        for (List<Integer> list : map.values()) {
            if (list.size() == 3 && list.get(1) - list.get(0) == list.get(2) - list.get(1)) {
                ans++;
            }
        }
        return ans;
    }
}
