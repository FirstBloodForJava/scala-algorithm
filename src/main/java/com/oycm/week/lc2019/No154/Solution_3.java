package com.oycm.week.lc2019.No154;

public class Solution_3 {

    /**
     * 1191. <a href="https://leetcode.cn/problems/k-concatenation-maximum-sum/description/">K 次串联后最大子数组之和</a> 1748
     *
     * @param arr
     * @param k
     * @return
     */
    public int kConcatenationMaxSum(int[] arr, int k) {
        /*
        给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
        例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
        返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
        由于 结果可能会很大，需要返回结果对 1000000007 取 模。
         */
        /*
        1 <= arr.length <= 1e5
        1 <= k <= 1e5
        -1e4 <= arr[i] <= 1e4
         */
        /*
        前缀和相关，arr 和有两种情况
            sum(arr) >= 0，数组后缀和 或 数组前缀和 是否大于 sum，(k-2) * sum(arr) + max(preMax, sum) + max(sufMax, sum)；
                以及和数组的最大前缀和相比，哪个更大
            sum(arr) < 0，单个数组最大前缀，或数组连接后的最大前缀和。
         */
        /*
        上面应该换成 max(preMax + sufMax, 2*sum)
        前后缀最大和，可以调整为就 2 个 arr 数组的最大子数组和。
        如果 sum > 0，sufMax = sum - preMin（前缀最小值）
                      preMax = max(sum)
            sufMax + preMax 就是 第一个数组的前缀最小值到末尾，加上第二个数组到数组前缀和最大值
         */
        int mod = 1000000007;
        // 最大前缀和
        long maxSum = 0;
        // 前缀最小值
        long minSum = 0;
        long sum = 0;
        long preMax = 0;
        for (int x : arr) {
            sum += x;
            preMax = Math.max(sum, preMax);
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        if (maxSum == 0 || k == 1) return (int) maxSum;

        // 两个数组拼接的最大前缀和
        long ans = 0;
        for (int x : arr) {
            sum += x;
            ans = Math.max(ans, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        // 后缀最大值
        long sufMax = 0;
        long suf = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            suf += arr[i];
            sufMax = Math.max(suf, sufMax);
        }

        return (int) (Math.max(ans, (k - 2) * suf + Math.max(preMax, suf) + Math.max(sufMax, suf)) % mod);
    }

}
