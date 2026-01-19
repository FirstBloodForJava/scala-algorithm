package com.oycm.algorithm.d.binary_search_first_k;


import java.util.Arrays;

public class Solution_3 {

    /**
     * 719. <a href="https://leetcode.cn/problems/find-k-th-smallest-pair-distance/description/">找出第 K 小的数对距离</a>
     * 数对 (a, b) 的距离定义为 a 和 b 的绝对差值
     *
     * @param nums
     * @param k
     * @return 求 第 k 小的数对距离
     */
    public int smallestDistancePair(int[] nums, int k) {
        /*
        数对距离为 dis, dis 越大, 符合要求的数对数量越多
        如果存在最小的 dis, 满足至少有 k 个数对距离 <= dis, 则 dis 就是答案
        二分答案, 怎么校验是否存在 k 个数对距离 <= dis
        可以用 滑动窗口计算 数对距离 <= dis 的方案数
         */
        Arrays.sort(nums);
        int left = -1, right = nums[nums.length - 1] - nums[0];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public boolean check(int[] nums, int dis, int k) {
        int cnt = 0;
        int n = nums.length, l = 0, r = 1;
        while (r < nums.length) {
            while (nums[r] - nums[l] > dis) {
                l++;
            }
            cnt += r - l;
            r++;
        }

        return cnt >= k;
    }

}
