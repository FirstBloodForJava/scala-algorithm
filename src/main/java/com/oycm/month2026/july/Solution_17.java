package com.oycm.month2026.july;

public class Solution_17 {

    /**
     * 3312. 查询排序后的最大公约数
     * <br>
     * 3312. <a href="https://leetcode.cn/problems/sorted-gcd-pair-queries/description/">查询排序后的最大公约数</a>
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] gcdValues(int[] nums, long[] queries) {
        /*
        给你一个长度为 n 的整数数组 nums 和一个整数数组 queries。
        gcdPairs 表示数组 nums 中所有满足 0 <= i < j < n 的数对 (nums[i], nums[j]) 的 最大公约数 升序 排列构成的数组。
        对于每个查询 queries[i] ，你需要找到 gcdPairs 中下标为 queries[i] 的元素。
        请你返回一个整数数组 answer ，其中 answer[i] 是 gcdPairs[queries[i]] 的值。
        gcd(a, b) 表示 a 和 b 的 最大公约数 。
         */
        /*
        2 <= n == nums.length <= 1e5
        1 <= nums[i] <= 5 * 1e4
        1 <= queries.length <= 1e5
        0 <= queries[i] < n * (n - 1) / 2
         */
        /*
        gcdPairs 数组长为 n * (n - 1) / 2，计算该数组
        题解思路：求每个 gcd 出现的次数。
        如何计算 gcd 恰好等于 2 的数对个数？
        统计 nums 中 2 的倍数个数，如果有 5 个。5 选 2，C(5, 2) = 10 个数对。
        但是这个 10 个数对的 gcd 并不都恰好等于，比如 gcd(8, 12) = 4。
        如果我们从 10 中减去 gcd 等于 4, 6, 8，就得到了 gcd 恰好等于 2 的数对个数
        一般地，定义 cntGcd[i] 表示 gcd 等于 i 的数对个数。
        枚举 i 的倍数，统计 nums 中有多少个数等于 i, 2i, 3i, ... 个数记作 c。
        这个 c 个数中选 2 个，组成 c(c - 1) / 2 数对。
        这些数对只是 i 的 倍数，并不恰好等于 i。
        将去 gcd 等于 2i, 3i, ... 的数对个数，得到如下递推式：
            cntGcd[i] = c(c - 1) / 2 - cntGcd[2i] - cntGcd[3i] - ...
        计算 cntGcd[i] 需要知道 cntGcd[2i] 的值，需要倒序枚举 i。

        接下来就是，答案查找。cntGcd 数组下标表示的就是数对中的 gcd，就是升序的。现在需要确定 q 在哪个 cntGcd[i] 中。
        前缀和 sumCntGcd[i] 表示，小于 i 的 gcd 有 sumCntGcd[i] 个。由于 cntGcd[0] 就是 0，原地计算前缀和。
        sumCntGcd[i] 表示 sumCntGcd[i] 个 gcd 小于等于 i。sumCntGcd 数组是升序的，因为 cntGcd[i] >= 0。
        可以在 sumCntGcd 上二分查找第一个大于 q 的下标，即当前的 q 的 gcd 值。
        为什么是第一个大于 q 的下标，不能等于，因为 sumCntGcd[i] 表示的是符合要求的数量，表示下标为 sumCntGcd[i] - 1
         */
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(x, mx);
        }
        int[] cntX = new int[mx + 1];
        for (int x : nums) {
            cntX[x]++;
        }
        long[] cntGcd = new long[mx + 1];
        for (int i = mx; i > 0; i--) {
            int c = 0;
            for (int j = i; j <= mx; j += i) {
                c += cntX[j];
                /*
                i == j 时 初始值就是 0，减去倍数的 gcd 数量
                 */
                cntGcd[i] -= cntGcd[j];
            }
            cntGcd[i] += (long) c * (c - 1) / 2;
        }
        // 原地计算前缀和
        for (int i = 2; i <= mx; i++) {
            cntGcd[i] += cntGcd[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = lowerBound(cntGcd, queries[i]);
        }
        return ans;
    }

    public int lowerBound(long[] nums, long target) {
        int l = -1, r = nums.length;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

}
