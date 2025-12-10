package com.oycm.algorithm.aba.advance;


import java.util.Arrays;

public class Solution_3 {


    /**
     * 1838. <a href="https://leetcode.cn/problems/frequency-of-the-most-frequent-element/description/">最高频元素的频数</a> 1876
     * <p>
     * 可以对 nums 中某个下标元素增加 1，记为一次操作，求执行最多 k 次操作，返回数组中能出现的最多频次
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maxFrequency(int[] nums, int k) {
        /*
        任意位置进行操作，可先对数组进行排序处理
        该怎么滑动，因为是进行 加法的操作次数，应该 从右到左 滑动
         */
        Arrays.sort(nums);
        int ans = 1;
        int r = nums.length - 1;
        int l = nums.length - 1;
        while (r > 0) {
            while (l >= 0 && nums[r] - nums[l] <= k) {
                k -= nums[r] - nums[l];
                l--;
            }
            int diff = r - l;
            ans = Math.max(ans, diff);
            // 返还 k
            k += (diff - 1) * (nums[r] - nums[r - 1]);
            r--;
        }



        return ans;
    }

    public static int leftToRight(int[] nums, int k) {
        /*
        题解思路：r 右移的过程，需要将 [0, r] 过程补齐 nums[r] - nums[i], 随着 r 的右移，这个值是越来越少的，符合滑动窗口的单调性
         */
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        // 要 减去的 nums[i] 和
        long sum = 0;
        int l = 0, r = 0;
        while (r < n) {
            sum += nums[r];
            while ((long) (r - l + 1) * nums[r] - sum > k) {
                sum -= nums[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

    public static void main(String[] args) {
        maxFrequency(new int[]{3, 6, 9}, 2);
    }

}
