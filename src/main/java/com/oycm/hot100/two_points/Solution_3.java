package com.oycm.hot100.two_points;

import java.util.*;

public class Solution_3 {

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
         */
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            set.clear();
            Set<Integer> vis = new HashSet<>();
            for (int j = i + 1; j < n; j++) {

                if (set.contains(-nums[i] - nums[j]) && !vis.contains(nums[j])) {
                    ans.add(List.of(nums[i], nums[j], -nums[i] - nums[j]));
                    vis.add(nums[j]);
                }
                set.add(nums[j]);
            }
        }

        return ans;
    }

}
