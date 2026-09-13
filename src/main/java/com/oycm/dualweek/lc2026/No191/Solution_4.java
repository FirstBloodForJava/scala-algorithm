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
        |s[j] - s[i] - goal| >= k, 记为 |s - goal| >= k
            s - goal >= k
            s - goal <= -k
            s >= k + goal || s <= goal - k
            那么不符合要求的前缀和值为 goal - k + 1 <= s[j] - s[i] <= goal + k - 1
        枚举 s[j]，那么 s[i] 需要满足：
            s[j] - goal - k + 1 <= s[i] <= g[j] - goal + k - 1
         */
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        // 离散化所有前缀和
        long[] sorted = sum.clone();
        Arrays.sort(sorted);
        long ans = (long) n * (n + 1) / 2;
        FenwickTree ft = new FenwickTree(sorted.length);
        for (long s : sum) {

            int l = search(sorted, s - goal - k + 1) + 1;
            int r = search(sorted, s - goal + k);
            ans -= ft.sumRange(l, r);
            // 保存 s[i] 出现的次数
            ft.update(search(sorted, s) + 1, 1);
        }

        return ans;
    }

    private int search(long[] nums, long target) {
        // 搜索 nums 中第一个大于等于 target 的下标
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    static class FenwickTree {
        private int[] tree;

        public FenwickTree(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            for (int i = index; i < tree.length; i += i & -i) {
                tree[i] += val;
            }
        }

        public int prefixSum(int i) {
            int s = 0;
            for (; i > 0; i &= i - 1) {
                s += tree[i];
            }
            return s;
        }

        // 求区间 [l, r]
        public int sumRange(int left, int right) {
            if (left > right) {
                return 0;
            }
            return prefixSum(right) - prefixSum(left - 1);
        }

    }

    public static void main(String[] args) {
        Solution_4 solution_1 = new Solution_4();
        System.out.println(solution_1.distantSubarrays(new int[]{-3, 1, 2}, 0, 3));
    }
}
