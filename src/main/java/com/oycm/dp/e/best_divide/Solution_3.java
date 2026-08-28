package com.oycm.dp.e.best_divide;

public class Solution_3 {

    /**
     * 3196. <a href="https://leetcode.cn/problems/maximize-total-cost-of-alternating-subarrays/description/">最大化子数组的总成本</a> 1847
     *
     * @param nums
     * @return
     */
    public long maximumTotalCost(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums。
        子数组 nums[l..r]（其中 0 <= l <= r < n）的 成本 定义为：
            cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)^(r−l)
        你的任务是将 nums 分割成若干子数组，使得所有子数组的成本之和 最大化，并确保每个元素 正好 属于一个子数组。
        具体来说，如果 nums 被分割成 k 个子数组，且分割点为索引 i1, i2, ..., ik − 1（其中 0 <= i1 < i2 < ... < ik - 1 < n - 1），则总成本为：
        cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
        返回在最优分割方式下的子数组成本之和的最大值。
        注意：如果 nums 没有被分割，即 k = 1，则总成本即为 cost(0, n - 1)。
         */
        /*
        dfs(i) 表示 [0, i] 数组分割的最大成本
        计算 [0, i] 不分隔的成本，枚举 [1, i-1] 作为分割点计算最大成本
        dfs(i) = cost(0, i)
        max(dfs(j) + cost(j+1, i)) 求最大值
        分割成 [0, l) [l, r] 取最大值 cost(l, r) = cost(0, l) - cost(0, r)
            cost(l, r)
                l 是偶数 = cost(0, r) - cost(0, l-1)
                l 是奇数 = cost(0, l-1) - cost(0, r)
        这样计算时间复杂度是 n^2 的
        +
        + -
        + - +
        + - + -
        + - + - +
        + - + - + -
        分割长为 3 的子数组，可以等价分为 1 + 2 或 2 + 1，大于 2 的都可以按照这个规则拆分，所以，可以只考虑这两种分割法案
        dfs(i) = max(
            dfs(i-1) + nums[i];
            dfs(i-2) + nums[i-1] - nums[i]
        )
        递归边界 dfs(-1) = 0, dfs(0) = nums[0]
         */
        int n = nums.length;
        long f0 = 0, f1 = nums[0];
        for (int i = 1; i < n; i++) {
            long f = f1;
            f1 = Math.max(f1 + nums[i], f0 + nums[i - 1] - nums[i]);
            f0 = f;
        }

        return f1;
    }


}
