package com.oycm.algorithm.h.log_trick;

public class Solution_2 {

    /**
     * 1521. <a href="https://leetcode.cn/problems/find-a-value-of-a-mysterious-function-closest-to-target/description/">找到最接近目标值的函数值</a> 2384
     *
     * @param arr
     * @param target
     * @return
     */
    public int closestToTarget(int[] arr, int target) {
        /*
        本质是求 arr 连续子数组与值 - target 的最小绝对值
         */
        int ans = Integer.MAX_VALUE;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            ans = Math.min(ans, Math.abs(x - target));

            for (int j = i - 1; j >= 0 && (arr[j] & x) != arr[j]; j--) {
                arr[j] &= x;
                ans = Math.min(ans, Math.abs(arr[j] - target));
            }
        }

        return ans;
    }

}
