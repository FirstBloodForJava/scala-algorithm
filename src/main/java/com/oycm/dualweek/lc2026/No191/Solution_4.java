package com.oycm.dualweek.lc2026.No191;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 统计遥远子数组的数目
     *
     * @param nums
     * @param goal
     * @param k
     * @return
     */
    public long distantSubarrays(int[] nums, int goal, int k) {
        /*
        给你一个整数数组 nums ，以及两个整数 goal 和 k 。
        如果一个子数组 nums[i..j] 满足其元素和与 goal 之间的绝对差至少为 k ，则称其为遥远的。
        返回遥远的子数组的数量。
        子数组是数组中连续的非空元素序列。
         */
        /*
        nums[i] 有负数，不能直接使用滑动窗口计算
         */
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        // 离散化所有前缀和
        long[] vals = sum.clone();
        Arrays.sort(vals);
        int m = 0;
        for (int i = 0; i < vals.length; i++) {
            if (i == 0 || vals[i] != vals[m - 1]) {
                vals[m++] = vals[i];
            }
        }

        FenwickTree fw = new FenwickTree(m);
        fw.update(search(vals, m, 0) + 1, 1);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long s = sum[i + 1];
            // vals 数组有序
            long lo = s - goal - k;
            long hi = s - goal + k;
            // [l, r],
            // l > lo 的第一个下标(>= lo + 1)；
            // r 是小于等于 hi 的最后一个下标，等价 第一个大于等于 hi 下标减 1
            long bad = fw.sumRange(search(vals, m, lo + 1), search(vals, m, hi) - 1);
            ans += (i + 1) - bad;
            fw.update(search(vals, m, sum[i + 1]) + 1, 1);
        }
        return ans;
    }

    private int search(long[] nums, int right, long target) {
        // 搜索 nums 中第一个大于等于 target 的下标
        int left = -1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    static class FenwickTree {
        private long[] tree;

        public FenwickTree(int n) {
            tree = new long[n + 1];
        }

        public void update(int index, int val) {
            for (int i = index; i < tree.length; i += i & -i) {
                tree[i] += val;
            }
        }

        public long prefixSum(int i) {
            long s = 0;
            for (; i > 0; i &= i - 1) {
                s += tree[i];
            }
            return s;
        }

        public long sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }

    }

    public static void main(String[] args) {
        Solution_4 solution_1 = new Solution_4();
        System.out.println(solution_1.distantSubarrays(new int[]{-3, 1, 2}, 0, 3));
    }
}
