package com.oycm.week2022.No283;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 2195. <a href="http://leetcode.cn/problems/append-k-integers-with-minimal-sum/description/">向数组中追加 K 个整数</a> 1659
     *
     * @param nums
     * @param k
     * @return
     */
    public long minimalKSum(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k 。请你向 nums 中追加 k 个 未 出现在 nums 中的、互不相同 的 正 整数，并使结果数组的元素和 最小 。
        返回追加到 nums 中的 k 个整数之和。
         */
        /*
        1 <= nums.length <= 1e5
        1 <= nums[i], k <= 1e9
         */
        /*
        把没有出现的最小值添加进去，排序后，相邻数字间填充数字
         */
        Arrays.sort(nums);
        long ans = 0;
        int pre = 0;
        for (int i = 0; i < nums.length && k > 0; i++) {
            int fill = nums[i] - pre - 1;
            if (fill <= 0) {
                pre = nums[i];
                continue;
            }
            if (fill >= k) {
                /*
                [pre+1, pre + k] k 个数
                 */
                return ans + (2L * pre + k + 1) * k / 2;
            }
            ans += (long) (pre + nums[i]) * fill / 2;

            k -= fill;
            pre = nums[i];
        }
        if (k > 0) {
            ans += (2L * pre + k + 1) * k / 2;
        }

        return ans;
    }

}
