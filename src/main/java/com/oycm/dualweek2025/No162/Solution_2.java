package com.oycm.dualweek2025.No162;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3634. <a href="https://leetcode.cn/problems/minimum-removals-to-balance-array/description/">使数组平衡的最少移除数目</a> 1453
     *
     * @param nums
     * @param k
     * @return
     */
    public int minRemoval(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k。
        如果一个数组的 最大 元素的值 至多 是其 最小 元素的 k 倍，则该数组被称为是 平衡 的。
        你可以从 nums 中移除 任意 数量的元素，但不能使其变为 空 数组。
        返回为了使剩余数组平衡，需要移除的元素的 最小 数量。
        注意：大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。
         */
        /*
        1 <= nums.length <= 1e5
        1 <= nums[i] <= 1e9
        1 <= k <= 1e5
         */
        /*
        满足 max <= k * min，数组是平衡的，不用移除元素
        up(max / k) <= min
        可以对数组排序
        当 max > k * min，只移除最小值，计算需要移除数量的最小值
         */
        Arrays.sort(nums);
        int n = nums.length;
        // 数组就是平衡的
        if ((nums[n - 1] - 1) / k + 1 <= nums[0]) return 0;
        int ans = n;
        // 固定 nums[r] 为最大值，计算需要移除哪些最小值
        int r = n - 1;
        // 不平衡
        while ((nums[r] - 1) / k + 1 > nums[0]) {
            // 查找第一个 >= mn = up(nums[r] / k)
            int l = lowerBound(nums, r, (nums[r] - 1) / k + 1);
            ans = Math.min(ans, l + n - 1 - r);
            r--;
        }
        ans = Math.min(ans, n - 1 - r);

        return ans;
    }

    public int lowerBound(int[] nums, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
