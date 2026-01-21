package com.oycm.datastructure.stack.monotonic.minimum_dic;


public class Solution_2 {

    /**
     * 1673. <a href="https://leetcode.cn/problems/find-the-most-competitive-subsequence/description/">找出最具竞争力的子序列</a> 1802
     * <p>
     * 最具 竞争力 的 nums 子序列定义: 相同长度下的子序列 a 和 子序列 b, 在第一个不相同的位置 越小越有竞争力
     *
     * @param nums
     * @param k    子序列长度
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        /*
        构造一个字典序最小的子序列, 如果剩余的数量足够构建 长为 k 的子序列, 且 栈顶 > nums[i], 弹出栈顶, 这样能构建一个更小的子序列
         */
        int n = nums.length;
        int[] ans = new int[k];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && ans[top] > nums[i] && n - i + top + 1 > k) {
                top--;
            }
            if (top < k - 1) {
                ans[++top] = nums[i];
            }

        }

        return ans;
    }

}
