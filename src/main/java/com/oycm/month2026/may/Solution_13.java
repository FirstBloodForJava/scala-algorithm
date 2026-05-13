package com.oycm.month2026.may;

public class Solution_13 {

    /**
     * 1674. <a href="https://leetcode.cn/problems/minimum-moves-to-make-array-complementary/description/">使数组互补的最少操作次数</a> 2333
     *
     * @param nums  长度为 偶数 n 的整数数组
     * @param limit 1 <= nums[i] <= limit <= 1e5
     * @return
     */
    public int minMoves(int[] nums, int limit) {
        /*
        每一次操作，你可以将 nums 中的任何整数替换为 [1, limit] 之间的另一个整数。
        如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的。
        例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
        返回使数组 互补 的 最少 操作次数。
         */
        /*
        暴力的计算，枚举每个 nums[i] + nums[n-1-i] 作为 互补和的操作数，i [0, n/2)，时间复杂度 n/2 * n/2 => n^2
        互补和最少操作次数有一个范围 [min, max]，以 min 为例
            如果存在一个互补和为 min，操作次数记为 m 次，其它 n/2 - 1 个数对，至少需要操作 m 次
            互补和为 min-1，假设其它数减少 1 都能满足要求，操作次数也至少为 m+1 次
         */
        /*
        题解思路：差分数组，设 x = nums[i], y = nums[n - 1 - i]，计算 x 和 y 变成 k 需要的操作次数
            操作 0 次，k = x + y；
            操作 1 次，k 的范围 [x + 1, x + limit], [y + 1, y + limit] 合并区间 [min(x, y) + 1, max(x, y) + limit]，表示区间中的数 x + y 操作一次可得到;
                注意，当前 k = x + y，操作 0 次，所有 d[k]++，d[k+1]--
            操作 2 次，k 可以变成 [2, limit*2] 范围内的任意数，需要排除操作 1 次得到的数区间，也就是
                [2, min(x, y)] ∪ [max(x, y) + limit + 1, 2 * limit] 的 k，操作两次可以得到
            创建要给 2 * limit + 2 数组，下标 i 表示所有 x + y 变成 i 需要操作的次数。
            遍历所有 x, y 就是初始化差分数组的过程
         */
        int n = nums.length;
        int m = 2 * limit;
        int[] d = new int[m + 2];
        for (int i = 0; i < n / 2; i++) {
            int x = nums[i];
            int y = nums[n - 1 - i];
            int l = Math.min(x, y) + 1;
            int r = Math.max(x, y) + limit;
            // +2
//            d[2] += 2;
//            d[l] -= 2;
            // +1
//            d[l]++;
            d[l]--;
//            d[r + 1]--;
            // k = x + y 操作 0 次
            d[x + y]--;
            d[x + y + 1]++;
            // + 2
//            d[r + 1] += 2;
            d[r + 1]++;
            // 2*limit + 1 可以不遍历

        }
        int ans = n;
        int sum = n;
        for (int i = 2; i <= m; i++) {
            sum += d[i];
            ans = Math.min(sum, ans);
        }

        return ans;
    }

}
