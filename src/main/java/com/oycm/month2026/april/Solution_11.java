package com.oycm.month2026.april;

import java.util.HashMap;
import java.util.Map;

public class Solution_11 {

    /**
     * 3741. <a href="https://leetcode.cn/problems/minimum-distance-between-three-equal-elements-ii/description/">三个相等元素之间的最小距离 II</a> 1450
     *
     * @param nums nums.length [1, 1e5], nums[i] [1, n]
     * @return 有效三元组 的 最小 可能距离, 不存在 有效三元组 ，返回 -1
     */
    public int minimumDistance(int[] nums) {
        /*
        nums[i] == nums[j] == nums[k], 且 (i, j, k) 是 3 个 不同 下标
        有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)
        */
        /*
        至少需要 3 个相同数才能组成 有效三元组
        key idxes[4] 0, 1 , 2 记录最新组成三元组 下标idxes[3] 记录当前三元组数量
        第一次构成的的三元素 [i, j, k] 再添加一个 k' 和 i 的距离只会更大
        优化：根据数据范围，使用数组记录上次，上上次下标
         */
        int n = nums.length;
        // 上一次出现下标
        int[] last = new int[n + 1];
        // 上上次出现下标
        int[] last2 = new int[n + 1];
        // 初始为 -n
        for (int i = 0; i <= n; i++) {
            last[i] = -n;
            last2[i] = -n;
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            ans = Math.min(ans, i - last2[x]);
            last2[x] = last[x];
            last[x] = i;
        }


        return ans == n ? -1 : ans * 2;
    }

}

class Solution_11_Self {
    public int minimumDistance(int[] nums) {
        /*
        至少需要 3 个相同数才能组成 有效三元组
        key idxes[4] 0, 1 , 2 记录最新组成三元组 下标idxes[3] 记录当前三元组数量
        第一次构成的的三元素 [i, j, k] 再添加一个 k' 和 i 的距离只会更大
         */
        int ans = Integer.MAX_VALUE;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int[] idxes = map.computeIfAbsent(nums[i], k -> new int[4]);
            idxes[idxes[3]++] = i;
            if (idxes[3] == 3) {
                // 更新答案
                ans = Math.min(ans, idxes[2] - idxes[0]);
                idxes[0] = idxes[1];
                idxes[1] = idxes[2];
                idxes[3] = 2;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans * 2;
    }
}
