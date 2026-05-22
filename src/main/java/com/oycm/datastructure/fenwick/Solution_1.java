package com.oycm.datastructure.fenwick;

public class Solution_1 {

    /**
     * 307. <a href="https://leetcode.cn/problems/range-sum-query-mutable/description/">区域和检索 - 数组可修改</a>
     */
    class NumArray {

        int[] tree;
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            tree = new int[n + 1];
            for (int i = 1; i < tree.length; i++) {
                tree[i] += nums[i - 1];
                int next = i + (i & -i);
                if (next <= n) {
                    tree[next] += tree[i];
                }
            }
        }

        /**
         * 将 nums[index] 的值 更新 为 val
         */
        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            // 关键区间 index 以及后面的 index + lowbit(index) 关键区间增加 delta
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        /**
         * 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和
         */
        public int sumRange(int left, int right) {
            return preSum(right + 1) - preSum(left);
        }

        public int preSum(int n) {
            int sum = 0;
            for (int i = n; i > 0; i &= i - 1) {
                sum += tree[i];
            }
            return sum;
        }
    }

}
