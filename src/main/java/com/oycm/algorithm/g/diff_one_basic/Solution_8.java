package com.oycm.algorithm.g.diff_one_basic;

public class Solution_8 {

    /**
     * 3355. <a href="https://leetcode.cn/problems/zero-array-transformation-i/description/">零数组变换 I</a> 1591
     *
     * @param nums    长度为 n 的整数数组, [1, 1e5]
     * @param queries queries[i] = [l, r]
     * @return
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        /*
        每个查询 queries[i]
            在 nums 的下标范围 [l, r] 内选择一个下标 子集(区间中任意挑选一些元素操作)
            将选中的每个下标对应的元素值减 1
        零数组：指所有元素都等于 0 的数组
        如果在按顺序处理所有查询后，可以将 nums 转换为 零数组 ，则返回 true，否则返回 false
         */
        /*
        将 nums 解析成一个差分数组，对差分数组进行查询操作，对差分数组进行还原，
            如果所以元素都小于等于 0 ,则返回 true；
            否则，返回 false
         */
        int n = nums.length;
        int[] diff = new int[n];
        diff[0] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
        for (int[] query : queries) {
            diff[query[0]] -= 1;
            if (query[1] < n - 1) {
                diff[query[1] + 1] += 1;
            }
        }
        if (diff[0] > 0) {
            return false;
        }
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > 0) {
                return false;
            }
        }

        return true;
    }

}
