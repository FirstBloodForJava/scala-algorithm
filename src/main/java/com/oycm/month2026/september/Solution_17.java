package com.oycm.month2026.september;

public class Solution_17 {

    /**
     * 1477. <a href="https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/description/">找两个和为目标值且不重叠的子数组</a> 1851
     *
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths(int[] arr, int target) {
        /*
        给你一个整数数组 arr 和一个整数值 target。
        请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值。
        请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
         */
        /*
        题解：前后缀分解。
        枚举第一个子数组的右端点 r，数组元素都是正数，可以使用滑动窗口维护一个子数组，如果 sum > target，左端点右移，遇到 sum = target，需要知道第二个子数组的最短长度：
            第二个子数组左端点 大于等于 r+1 时，最短长度
        后缀长度可以预处理，倒序枚举，枚举子数组的左端点 l = n-1, r = n-1, 如果 sum > target，右端点左移，遇到 sum = target，更新最短长度
         */
        int n = arr.length;

        int[] sufMin = new int[n];
        int minLen = n + 1;
        int sum = 0;
        int r = n - 1;
        for (int l = n - 1; l > 0; l--) {
            sum += arr[l];
            while (sum > target) {
                sum -= arr[r];
                r--;
            }
            if (sum == target) {
                minLen = Math.min(minLen, r - l + 1);
            }
            sufMin[l] = minLen;
        }
        int ans = n + 1;
        sum = 0;
        int l = 0;
        for (r = 0; r < n - 1; r++) {
            sum += arr[r];
            while (sum > target) {
                sum -= arr[l];
                l++;
            }
            if (sum == target) {
                ans = Math.min(ans, r - l + 1 + sufMin[r + 1]);
            }
        }
        return ans > n ? -1 : ans;
    }

}
