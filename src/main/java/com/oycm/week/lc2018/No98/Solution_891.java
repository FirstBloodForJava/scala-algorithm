package com.oycm.week.lc2018.No98;

import java.util.Arrays;

public class Solution_891 {

    /**
     * 891. 子序列宽度之和
     * <br>
     * 891. <a href="https://leetcode.cn/problems/sum-of-subsequence-widths/description/">子序列宽度之和</a> 2183
     *
     * @param nums
     * @return
     */
    public int sumSubseqWidths(int[] nums) {
        /*
        一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
        给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 1e9 + 7 取余 后的结果。
        子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
         */
        /*
        题解思路：
        子序列和元素的顺序无关，这里只需要关注最大/最小值。考虑排序。
        排序后，枚举每个 nums[i] 作为最大值，计算其所有序列宽度。
        [0, i-1] [i] [i+1, n)
            [0, i-1] 的元素，可以选/不选，有 2^i 种选法；
            [i] 必须选；
            [i+1, n) 不能选
        x = nums[i]
        x 作为最大值子序列个数 2^i
        x 作为最小值子序列个数 2^(n-1-i)，x 右边有 n-1-i 个数可以选/不选。
        x 对答案的贡献为
            作为最大值 x * 2^i
            作为最小值 -x * 2^(n-1-i)
        累加所有贡献，得到答案
            nums[i] * (2^i - 2^(n-1-i))
        公式转换，提取 2^i，找要被减去的 nums[j] 下标是什么
            j = n - i - 1, 作为最小值，有 2^i 个子序列 n-1 - j
        */
        int mod = 1000000007;
        Arrays.sort(nums);
        int n = nums.length;
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = 2 * pow2[i - 1] % mod;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) (pow2[i] - pow2[n - 1 - i]) * nums[i];
        }

        return (int) (ans % mod + mod) % mod;

    }

}
