package com.oycm.month2026.august;

import java.util.Arrays;

public class Solution_29 {

    /**
     * 2948. <a href="https://leetcode.cn/problems/make-lexicographically-smallest-array-by-swapping-elements/description/">交换得到字典序最小的数组</a> 2047
     *
     * @param nums
     * @param limit
     * @return
     */
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        /*
        给你一个下标从 0 开始的 正整数 数组 nums 和一个 正整数 limit。
        在一次操作中，你可以选择任意两个下标 i 和 j，如果 满足 |nums[i] - nums[j]| <= limit ，则交换 nums[i] 和 nums[j] 。
        返回执行任意次操作后能得到的 字典序最小的数组 。
        如果在数组 a 和数组 b 第一个不同的位置上，数组 a 中的对应元素比数组 b 中的对应元素的字典序更小，则认为数组 a 就比数组 b 字典序更小。
        例如，数组 [2,10,3] 比数组 [10,2,3] 字典序更小，下标 0 处是两个数组第一个不同的位置，且 2 < 10 。
         */
        /*
        |nums[i] - nums[j]| <= limit
        nums = [6, 3, 2, 1], limit = 3, 那么 这个数组中元素下标位置可以任意交换
        如果 1 要到下标 0，可以让 3 先交换到下标 0，再和 1 交换。
        如果能把一堆可交换的下标记录好，再按从小到大的顺序安置位置，就能得到最小子序列数组。
        怎么维护一个连通块的元素。
        直接枚举所有元素构建连通块是 n^2。
        可以对 nums 排序，如果 nums[0] 和 nums[1] 在一个连通块，nums[1] 和 nums[2] 在一个连通块，那么 0 和 2 也在一个连通块。
        直接排序不行，会丢失原有下标，可以创建一个下标数组 pos，nums[pos[i]] 进行排序，那么 nums[pos[i]] 是有序的
         */
        int n = nums.length;
        Integer[] pos = new Integer[n];
        // 设置下标
        Arrays.setAll(pos, i -> i);
        // 数组下标排序
        Arrays.sort(pos, (a, b) -> nums[a] - nums[b]);
        int[] ans = new int[n];
        int start = 0;
        for (int i = 0; i < n; i++) {
            // 最后一段、新的连通块
            if (i == n - 1 || nums[pos[i + 1]] - nums[pos[i]] > limit) {
                // [start, i] 为新的连通块，将区间的下标，从小到大放置元素
                Integer[] subPos = Arrays.copyOfRange(pos, start, i + 1);
                Arrays.sort(subPos);
                for (int j = 0; j < subPos.length; j++) {
                    ans[subPos[j]] = nums[pos[start + j]];
                }
                // 下一段开始
                start = i + 1;
            }

        }
        return ans;
    }

}
