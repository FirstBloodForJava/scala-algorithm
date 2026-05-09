package com.oycm.hot100.two_points;

public class Solution_4 {

    /**
     * 42. <a href="https://leetcode.cn/problems/trapping-rain-water/description/">接雨水</a>
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        /*
        给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
         */
        /*
        [2, 3, 6, 3, 2, 0, 1, 5]
        上面数组表示柱子的高，观察可以发现，只有当左边有高柱子，右边有高柱子，形成中间的低洼，才能接住雨水
         */
        /*
        前后缀分解：
            preMax[], preMax[i] 表示 [0, i] 区间的最大高度
            sufMax[], sufMax[i] 表示 [i, n-1] 区间的最大高度
        对于 nums[i] min(preMax[i], sufMax[i]) >= height[i] 大于 height[i] 的部分，就是能接的雨水

         */
        int ans = 0;
        int n = height.length;
        int[] sufMax = new int[n];
        sufMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }
        int preMax = height[0];
        for (int i = 0; i < n; i++) {
            preMax = Math.max(preMax, height[i]);
            ans += Math.min(preMax, sufMax[i]) - height[i];

        }

        return ans;
    }

    public int towPointsTrap(int[] height) {
        /*
        相向双指针：
            维护前缀最大值及下标
            维护后缀最大值及下标
            原理和前后缀一致
         */
        int ans = 0;
        int preMax = 0;
        int sufMax = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);
            if (preMax < sufMax) {
                ans += preMax - height[left];
                left++;
            } else {
                ans += sufMax - height[right];
                right--;
            }
        }
        return ans;
    }

}
