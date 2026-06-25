package com.oycm.hot100.two_points;

import java.util.*;

public class Solution_6 {

    /**
     * 15. <a href="https://leetcode.cn/problems/3sum/description/">三数之和</a>
     *
     * @param nums 整数数组, nums.length [3, 3000]
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /*
        判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
        返回所有和为 0 且不重复的三元组。
         */
        /*
        a + b + c = 0
        a + b = -c
        i [0, n-3] 枚举 nums[i], 看 [i+1, n-1] 中是否存在 a + b = -c 的值
        怎么做去重判断？
            先对数组排序，从小到大遍历
            -10 + b + c = 0, [i+1, n-1] 中找到两数之和为 10 b+c
        [i+1, n-1] 区间找 i,j 使得 nums[i] + nums[j] + nums[k] == 0
        数组有序要找 nums[j] + nums[k] == -nums[i]
        暴力的做法：
            j [i+1, n-1] 循环
            j+1 [j+1, n-1] 循环
        数组有序，最小值 + 最大值 和 -nums[i] 有哪些情况呢？
            最小值 + 最大值 = -nums[i] 符合要求
            最小值 + 最大值 < -nums[i] 最小值和最大值相加都小于 -nums[i]，说明最小值和任意数相加都不会符合条件，就不用和后续比较了
            最小值 + 最大值 > -nums[i] 最小值和最大值相加都大于 -nums[i]，说明最大值和任意数相加都不会符合条件，就不用和后续比较了
        排序之后，不用 hash 记录左边的值
        时间复杂度 O(n^2)
         */
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }
            // 优化一：最小值之和都大于 0
            if (x + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            // 优化二：当前值 + 两个最大值都小于 0
            if (x + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = x + nums[j] + nums[k];
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    // 三数之和为 0
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    // 跳过重复数字
                    for (j++; j < k && nums[j] == nums[j - 1]; j++) ;
                    for (k--; j < k && nums[k] == nums[k + 1]; k--) ;
                }
            }
        }

        return ans;
    }

}
