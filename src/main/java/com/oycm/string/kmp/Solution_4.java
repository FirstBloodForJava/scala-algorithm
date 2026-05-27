package com.oycm.string.kmp;

public class Solution_4 {

    /**
     * 3036. <a href="https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-ii/description/">匹配模式数组的子数组数目 II</a> 1895
     *
     * @param nums
     * @param pattern
     * @return
     */
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        /*
        2 <= n == nums.length <= 1e6
        1 <= nums[i] <= 1e9
        1 <= m == pattern.length < n
        给你一个下标从 0 开始长度为 n 的整数数组 nums ，和一个下标从 0 开始长度为 m 的整数数组 pattern ，pattern 数组只包含整数 -1，0 和 1。
        大小为 m + 1 的子数组 nums[i..j] 如果对于每个元素 pattern[k] 都满足以下条件，那么我们说这个子数组匹配模式数组 pattern：
            如果 pattern[k] == 1 ，那么 nums[i + k + 1] > nums[i + k]
            如果 pattern[k] == 0 ，那么 nums[i + k + 1] == nums[i + k]
            如果 pattern[k] == -1 ，那么 nums[i + k + 1] < nums[i + k]
        */
        /*
        [i+0, i+1, i+2, i+2, ..., i+m]
            pattern[0] = 1 意味着 nums[i+1] > nums[i]; nums[i+1] - nums[i] > 0;
            pattern[0] = 0 意味着 nums[i+1] = nums[i]; nums[i+1] - nums[i] = 0;
            pattern[0] = -1 意味着 nums[i+1] < nums[i];nums[i+1] - nums[i] < 0;
        可以把 nums[i+1] - nums[i] 结果，看作 -1，0 和 1，问题转换成求 nums 有多少个连续子数组 pattern
        问题就转换成了 kmp，求 字符串中求有多少个子串
         */
        int ans = 0;
        int m = pattern.length;
        int[] next = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = next[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            next[i] = cnt;
        }

        for (int i = 1, j = 0; i < nums.length; i++) {
            int x = Integer.compare(nums[i], nums[i - 1]);
            while (j > 0 && x != pattern[j]) {
                j = next[j - 1];
            }
            if (x == pattern[j]) {
                j++;
            }
            if (j == m) {
                ans++;
                j = next[j - 1];
            }
        }

        return ans;
    }

}
