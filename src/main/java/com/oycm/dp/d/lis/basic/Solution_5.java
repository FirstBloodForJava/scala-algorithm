package com.oycm.dp.d.lis.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_5 {

    /**
     * 2111. <a href="https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/description/">使数组 K 递增的最少操作次数</a>
     *
     * @param arr
     * @param k
     * @return
     */
    public int kIncreasing(int[] arr, int k) {
        /*
        给你一个下标从 0 开始包含 n 个正整数的数组 arr ，和一个正整数 k 。
        如果对于每个满足 k <= i <= n-1 的下标 i ，都有 arr[i-k] <= arr[i] ，那么我们称 arr 是 K 递增 的。
        比方说，arr = [4, 1, 5, 2, 6, 2] 对于 k = 2 是 K 递增的，因为：
            arr[0] <= arr[2] (4 <= 5)
            arr[1] <= arr[3] (1 <= 2)
            arr[2] <= arr[4] (5 <= 6)
            arr[3] <= arr[5] (2 <= 2)
        但是，相同的数组 arr 对于 k = 1 不是 K 递增的（因为 arr[0] > arr[1]），对于 k = 3 也不是 K 递增的（因为 arr[0] > arr[3] ）。
        每一次 操作 中，你可以选择一个下标 i 并将 arr[i] 改成任意 正整数。
        请你返回对于给定的 k ，使数组变成 K 递增的 最少操作次数 。
         */
        /*
        按以下规则，分为 k 个数组，求每个数组的最长非递减子序列
        0, k, 2k, ...;
        1, k+1, 2k+1, ...;
        ...
        k-1, 2k-1, 3k-1, ...;
         */
        int n = arr.length;
        List<Integer>[] gs = new List[k];
        Arrays.setAll(gs, l -> new ArrayList());
        for (int K = 0; K < k; K++) {
            for (int j = K; j < n; j += k) {
                gs[K].add(arr[j]);
            }
        }
        int ans = n;
        for (List<Integer> g : gs) {
            ans -= lcs(g);
        }

        return ans;
    }

    public int lcs(List<Integer> nums) {
        int gn = 0;
        for (Integer x : nums) {
            int j = lowerBound(nums, gn, x);
            nums.set(j, x);
            if (j == gn) {
                gn++;
            }

        }
        return gn;
    }

    public int lowerBound(List<Integer> nums, int r, int target) {
        int l = -1;
        // 开区间，查询 nums 中第一个大于 target 的下标
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums.get(mid) > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
}
