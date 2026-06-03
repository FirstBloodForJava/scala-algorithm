package com.oycm.dualweek2025.No162;

public class Solution_4 {

    /**
     * 3636. <a href="https://leetcode.cn/problems/threshold-majority-queries/description/">查询超过阈值频率最高元素</a> 2451
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        /*
        给你一个长度为 n 的整数数组 nums 和一个查询数组 queries，其中 queries[i] = [li, ri, thresholdi]。
        返回一个整数数组 ans，其中 ans[i] 等于子数组 nums[li...ri] 中出现 至少 thresholdi 次的元素，
        选择频率 最高 的元素（如果频率相同则选择 最小 的元素），如果不存在这样的元素则返回 -1。
         */
        /*
        1 <= nums.length == n <= 1e4
        1 <= nums[i] <= 1e9
        1 <= queries.length <= 5 * 1e4
        queries[i] = [li, ri, thresholdi]
        0 <= li <= ri < n
        1 <= thresholdi <= ri - li + 1
         */
        /*
        todo 莫队算法
         */

        return nums;
    }

}
