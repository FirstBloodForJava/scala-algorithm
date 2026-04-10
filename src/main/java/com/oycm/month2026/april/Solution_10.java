package com.oycm.month2026.april;

public class Solution_10 {

    /**
     * 3740. <a href="https://leetcode.cn/problems/minimum-distance-between-three-equal-elements-i/description/">三个相等元素之间的最小距离 I</a> 1288
     *
     * @param nums
     * @return 有效三元组 的 最小 可能距离, 不存在 有效三元组 ，返回 -1
     */
    public int minimumDistance(int[] nums) {
        /*
        nums[i] == nums[j] == nums[k], 且 (i, j, k) 是 3 个 不同 下标
        有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)
         */
        /*
        假设 i > j > k
        i - j + j - k + -(k - i) = 2 * (i - k) = 2 * (max(i, j, k) - min(i, j, k))
        暴力 n ^ 2
         */
        int ans = Integer.MAX_VALUE;
        out:
        for (int i = 0; i < nums.length; i++) {
            // 这里嵌套循环只会执行 n 次
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[k] == nums[i]) {
                            ans = Math.min(ans, 2 * (k - i));
                            continue out;
                        }
                    }
                }

            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
