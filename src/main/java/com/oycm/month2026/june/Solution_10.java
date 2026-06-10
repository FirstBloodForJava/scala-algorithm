package com.oycm.month2026.june;

import java.util.PriorityQueue;

public class Solution_10 {

    /**
     * 3691. <a href="https://leetcode.cn/problems/maximum-total-subarray-value-ii/description/">最大子数组总值 II</a> 2469
     *
     * @param nums
     * @param k
     * @return
     */
    public long maxTotalValue(int[] nums, int k) {
        /*
        给你一个长度为 n 的整数数组 nums 和一个整数 k。
        你必须从 nums 中选择 恰好 k 个 不同 的非空子数组 nums[l..r]。子数组可以重叠，但同一个子数组（相同的 l 和 r）不能 被选择超过一次。
        子数组 nums[l..r] 的 值 定义为：max(nums[l..r]) - min(nums[l..r])。
        总值 是所有被选子数组的 值 之和。
        返回你能实现的 最大 可能总值。
        子数组 是数组中连续的 非空 元素序列。
         */
        /*
        定义一个 n * n 的矩阵，grid[l][r] 表示 [l, r] 子数组的值；
        当 l > r 是，grid[l][r] 默认为 0，这样就得到了一个每行非递减的矩阵，
        非递减的原因：左端点固定，右端点右移，最小值不断变小，最大值不断变大
        非递减矩阵找出前 k 大之和。
        st 表来快速查询区间最值，st[i][k] 表示 [i, i+2^k-1] 区间的最值，可以预处理 数组中所有 2^k 的最值，
        [l, r]; k = log(r-l+1), 区间的最值可以拆分成两个区间查找 [l, l+2^k-1] 和 [r-2^k+1, r]
        拆分成两个 2^k 的区间 (st[i][k], st[r-2^k+1][k])
        则需要预处理所有 nums[i] 2^k 的最值
        st[i][k] = st[i][k-1], st[i+2^(k-1][k-1])
         */
        int n = nums.length;
        ST st = new ST(nums);
        // 三元组 val, l, r
        PriorityQueue<int[]> max = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            max.add(new int[]{st.find(i, n), i, n});
        }
        long ans = 0;
        while (k-- > 0 && max.peek()[0] > 0) {
            int[] top = max.poll();
            ans += top[0];
            top[2]--;
            top[0] = st.find(top[1], top[2]);
            max.add(top);
        }

        return ans;
    }

    class ST {
        private int[][] stMin;
        private int[][] stMax;

        public ST(int[] nums) {
            int n = nums.length;
            int k = 32 - Integer.numberOfLeadingZeros(n);
            /*
            为了优化预处理计算：st[i][k] = st[i][k-1], st[i+2^(k-1][k-1])
            k-1 变成行
            st[j][i] 表示 [i, i+2^j-1] 最值
             */
            stMin = new int[k][n];
            stMax = new int[k][n];

            for (int i = 0; i < n; i++) {
                stMin[0][i] = nums[i];
                stMax[0][i] = nums[i];
            }

            // 预处理
            for (int j = 1; j < k; j++) {
                // [i, i+2^j)
                for (int i = 0; i + (1 << j) <= n; i++) {
                    stMin[j][i] = Math.min(stMin[j - 1][i], stMin[j - 1][i + (1 << (j - 1))]);
                    stMax[j][i] = Math.max(stMax[j - 1][i], stMax[j - 1][i + (1 << (j - 1))]);
                }
            }
        }

        public int find(int l, int r) {
            int k = 31 - Integer.numberOfLeadingZeros(r - l);
            int mx = Math.max(stMax[k][l], stMax[k][r - (1 << k)]);
            int mn = Math.min(stMin[k][l], stMin[k][r - (1 << k)]);
            return mx - mn;
        }
    }

}
