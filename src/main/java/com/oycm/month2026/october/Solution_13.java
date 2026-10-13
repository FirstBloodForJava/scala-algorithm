package com.oycm.month2026.october;

import java.util.Arrays;

public class Solution_13 {

    /**
     * 3727. <a href="https://leetcode.cn/problems/maximum-alternating-sum-of-squares/description/">最大交替平方和</a> 1455
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        /*
        给你一个整数数组 nums。你可以以任意顺序 重新排列元素 。
        数组 arr 的 交替得分 定义为：
            score = arr[0]^2 - arr[1]^2 + arr[2]^2 - arr[3]^2 + ...
        在对 nums 重新排列后，返回其 最大可能的交替得分。
         */
        /*
        按绝对值排序
        nums.length = n, m = (n+1)/2 个元素发展偶数下标，最大数平方和 减去 最小数平方和
        1.6 10^9 * 10^5/2
         */
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        // 前一半下取整
        int m = n / 2;
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans -= nums[i];
        }
        for (int i = m; i < n; i++) {
            ans += nums[i];
        }

        return ans;
    }

}
