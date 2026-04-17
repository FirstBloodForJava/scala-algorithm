package com.oycm.month2026.april;

import java.util.HashMap;
import java.util.Map;

public class Solution_17 {

    /**
     * 3761. <a href="https://leetcode.cn/problems/minimum-absolute-distance-between-mirror-pairs/description/">镜像对之间最小绝对距离</a> 1669
     *
     * @param nums nums.length [1, 1e5]; nums[i] [1, 1e9]
     * @return 返回任意镜像对的下标之间的 最小绝对距离
     */
    public int minMirrorPairDistance(int[] nums) {
        /*
        镜像对 是指一对满足下述条件的下标 (i, j)：
            0 <= i < j < nums.length，并且
            reverse(nums[i]) == nums[j]，其中 reverse(x) 表示将整数 x 的数字反转后形成的整数。反转后会忽略前导零，例如 reverse(120) = 21。
         */
        /*
        用 hash 表记录 前面 nums[i] 的 reverse(nums[i]) 最大下标，先查询，后更新
         */
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = n;
        for (int i = 0; i < n; i++) {
            int rev = 0;
            int temp = nums[i];
            while (temp > 0) {
                rev = rev * 10 + temp % 10;
                temp /= 10;
            }
            ans = Math.min(ans, i - map.getOrDefault(nums[i], -n));
            map.put(rev, i);
        }

        return ans == n ? -1 : ans;

    }

}
