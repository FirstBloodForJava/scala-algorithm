package com.oycm.month2026.september;

public class Solution_23 {

    /**
     * 1658. <a href="https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/description/">将 x 减到 0 的最小操作数</a> 1817
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        /*
        给你一个整数数组 nums 和一个整数 x 。
        每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
        如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
         */
        /*
        前后缀分解，转换成是否存在最短的前后缀，两者之和为 x
         */
        /*
        逆向思维 + 滑动窗口做法：计算最长的何为 sum - x 的子数组长度。
         */
        int sum = 0;
        int n = nums.length;
        int right = n;
        while (right > 0 && sum + nums[right - 1] <= x) {
            right--;
            sum += nums[right];
        }
        if (sum < x && right == 0) {
            return -1;
        }
        int ans = sum == x ? n - right : n + 1;
        for (int left = 0; left < n; left++) {
            sum += nums[left];
            while (right < n && sum > x) {
                // 删除 [0, left] 和 [right, n) 多了，right 和任意的 left 都不符合要求
                sum -= nums[right];
                right++;
            }
            if (sum > x) {
                // 右端点已经全部去掉，前缀部分过长
                break;
            }
            if (sum == x) {
                ans = Math.min(ans, left + 1 + n - right);
            }
        }
        return ans > n ? -1 : ans;
    }

}
