package com.oycm.dp.d.lcs.basic;

public class Solution_5 {

    /**
     * 1035. <a href="https://leetcode.cn/problems/uncrossed-lines/description/">不相交的线</a> 1806
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        /*
        在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
        现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
            nums1[i] == nums2[j]
            且绘制的直线不与任何其他连线（非水平线）相交。
        请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
        以这种方法绘制线条，并返回可以绘制的最大连线数。
         */
        /*
        dfs(i, j) =
            nums1[i] = nums2[j]: dfs(i-1, j-1) + 1
            nums1[i] != nums2[j]: max(dfs(i, j-1), dfs(i-1, j)
        会有其它情况未考虑到吗？
         */
        int m = nums2.length;
        int[] f = new int[m + 1];
        for (int x : nums1) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                int temp = f[j + 1];
                f[j + 1] = x == nums2[j] ? pre + 1 : Math.max(f[j], f[j + 1]);
                pre = temp;
            }
        }

        return f[m];
    }
}
