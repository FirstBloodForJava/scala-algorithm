package com.oycm.dp.e.enable_divide;

public class Solution_1 {

    /**
     * 2369. <a href="https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array/description/">检查数组是否存在有效划分</a> 1780
     *
     * @param nums
     * @return
     */
    public boolean validPartition(int[] nums) {
        /*
        给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为 一个或多个 连续 子数组。
        如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
            子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
            子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
            子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
        如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
         */
        /*
        dfs(i) 表示 i 结尾的子数组是否能有效划分，分类讨论：
            如果 i > 0 && nums[i] == nums[i-1]，问题转换成 dfs(i-2) 是否有效划分；
            如果 i > 1 && nums[i] == nums[i-1] == nums[i-2]，问题转换成 dfs(i-3) 是否有效划分；
            如果 i > 1 && nums[i] == nums[i-1]+1 == nums[i-2]+2，问题转换成 dfs(i-3) 是否有效划分；
        递归边界 i < 0 时，有效划分。
        为了避免 i < 0 的情况，可以修改 dfs(i) 定义，dfs(i+1) 表示 i 结尾长 i+1 的子数组能否有效划分：
        dfs(i+1) =
            dfs(i-1) && (两个相等元素)
            dfs(i-2) && (三个相等元素)
            dfs(i-2) && (三个连续递增元素)
         */
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            if (f[i - 1] && nums[i] == nums[i - 1] || i > 1
                    && f[i - 2] && (
                    nums[i] == nums[i - 1] && nums[i] == nums[i - 2] ||
                    nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2
            )) {
                f[i + 1] = true;
            }
        }

        return f[n];
    }


}
