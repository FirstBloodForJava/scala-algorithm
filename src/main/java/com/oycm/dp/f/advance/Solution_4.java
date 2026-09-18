package com.oycm.dp.f.advance;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 1186. <a href="https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/description/">删除一次得到子数组最大和</a> 1799
     *
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        /*
        给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。
        换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
        注意，删除一个元素后，子数组 不能为空。
         */
        /*
        题解：状态机 dp
         */
        int n = arr.length;
        int[][] f = new int[n + 1][2];
        Arrays.fill(f[0], Integer.MIN_VALUE / 2);
        int ans = Integer.MIN_VALUE;
        // f[i+1][0] 表示子数组右端点下标为 i，不删除数字的最大值
        // f[i+1][1] 表示子数组右端点下标为 i，必须删除一个数组的最大值
        for (int i = 0; i < n; i++) {
            f[i + 1][0] = Math.max(f[i][0], 0) + arr[i];
            f[i + 1][1] = Math.max(f[i][1] + arr[i], f[i][0]);
            ans = Math.max(ans, Math.max(f[i + 1][0], f[i + 1][1]));
        }

        return ans;
    }

}
