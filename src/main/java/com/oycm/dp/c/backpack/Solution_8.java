package com.oycm.dp.c.backpack;

public class Solution_8 {

    /**
     * 3489. <a href="https://leetcode.cn/problems/zero-array-transformation-iv/description/">零数组变换 IV</a> 2068
     *
     * @param nums    nums.length [1, 10]; nums[i] [0, 1000]
     * @param queries queries.length [1, 1000];
     *                queries[i] = [l, r, val];
     *                0 <= l <= ri < nums.length;
     *                1 <= val <= 10
     * @return
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        /*
        给你一个长度为 n 的整数数组 nums 和一个二维数组 queries ，其中 queries[i] = [l, r, val]。
        每个 queries[i] 表示以下操作在 nums 上执行：
            从数组 nums 中选择范围 [l, r] 内的一个下标子集。
            将每个选中下标处的值减去 正好 val。
        零数组 是指所有元素都等于 0 的数组。
        返回使得经过前 k 个查询（按顺序执行）后，nums 转变为 零数组 的最小可能 非负 值 k。如果不存在这样的 k，返回 -1。
        数组的 子集 是指从数组中选择的一些元素（可能为空）。
         */
        /*
        有想到选一些数是否能和为 nums[i]，没想到怎么选
        题解思路：
        把询问当成一个数组 vals，有一些数一定不能选，从 vals 前缀中是否能选一些数，使得其和 nums[i]，0-1 背包 选一些数和为 target 的最短前缀
         */
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            int x = nums[i];
            boolean[] f = new boolean[x + 1];
            f[0] = true;
            for (int j = 0; j < queries.length; j++) {
                int[] q = queries[j];
                if (i < q[0] || i > q[1]) continue;
                int val = q[2];
                for (int v = x; v >= val; v--) {
                    f[v] = f[v] || f[v - val];
                }
                // [0, j] 存在符合要求的方案数
                if (f[x]) {
                    ans = Math.max(ans, j + 1);
                    break;
                }
            }
            // 执行完成不符合要求
            if (!f[x]) {
                return -1;
            }
        }

        return ans;
    }

}
