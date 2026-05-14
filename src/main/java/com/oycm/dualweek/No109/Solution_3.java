package com.oycm.dualweek.No109;

public class Solution_3 {

    /**
     * 2786. <a href="https://leetcode.cn/problems/visit-array-positions-to-maximize-score/description/">访问数组中的位置使分数最大</a> 1733
     *
     * @param nums nums[i], x [1, 1e6]; nums.length [2, 1e5]
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        /*
        一个下标从 0 开始的整数数组 nums 和一个正整数 x。
        一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
            如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意位置 j。
            对于你访问的位置 i ，你可以获得分数 nums[i]。
            如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x。

        返回你能得到的 最大 得分之和。
         */
        /*
        设 f(i) 表示 0 到 i 能获得的最大得分，分类讨论：
            如果 nums[i] % 2 == nums[i-1] % 2, f(i) = f(i-1) + nums[i]
            如果 nums[i] % 2 != nums[i-1] % 2, f(i) = f(i-1) + nums[i] - x
                如果 nums[i] >= x，直接计算，没有问题
                如果 nums[i] < x，nums[i+1] % 2 != nums[i] % 2，这里可以直接跳过 i，从 i-1 直接到 i + 1
                这种情况该怎么处理呢？
                f = max(f + nums[i] - x, ints[nums[i] % 2] + nums[i])
                初始化一个长为 2 的数组 ints，
                ints[0] 表示 [0, i] 之间，最后一个偶数 nums[j] 能获得的最大得分
                ints[1] 表示 [0, i] 之间，最后一个奇数 nums[j] 能获得的最大得分
                ints[0] = ints[1] = -x + nums[0], ints[nums[0] % 2] = nums[0]
                nums[i] % 2 != nums[i-1] % 2 时，两种到达 i 的方式，i-1 到达，从上一个相同的奇偶性下标到达
         */
        long ans = nums[0];
        long f = ans;
        long[] ints = new long[]{-x + ans, -x + ans};
        ints[nums[0] & 1] = f;
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] & 1) == (nums[i - 1] & 1)) {
                f += nums[i];
            } else {
                /*
                前一个位置过来：f + nums[i] - x
                前一个相同奇偶最大得分过来：
                 */
                f = Math.max(f + nums[i] - x, ints[nums[i] & 1] + nums[i]);
            }
            ints[nums[i] & 1] = Math.max(ints[nums[i] & 1], f);
            ans = Math.max(ans, f);
        }

        return ans;
    }

}
