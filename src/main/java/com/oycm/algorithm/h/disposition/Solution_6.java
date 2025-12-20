package com.oycm.algorithm.h.disposition;

public class Solution_6 {

    /**
     * 3153. <a href="https://leetcode.cn/problems/sum-of-digit-differences-of-all-pairs/description/">所有数对中数位差之和</a> 1645
     * <p>
     * 两个整数的数位差: 两个整数相同位置上不同数字的数目
     *
     * @param nums
     * @return 求 nums 中所有整数对里的数位差之和
     */
    public long sumDigitDifferences(int[] nums) {
        /*
        13
        23
        12
        13, 23 1;
        13, 12 1;
        23, 12 2;
        问题可以拆分成
            在 个位数有多少数 数位不同
            在 十位数有多少数 数位不同
        问题转换成 一个 长为 n 的数组, 数组中只有 0-9, 求数组中不同数的对数
        用一个数组 a = int[10] 记录 0-9 出现的次数, 表示当前第几个数 k - a[?] 当前数和其它数不同的对数
         */
        long ans = 0;
        int n = nums.length;
        int m = String.valueOf(nums[0]).length();
        int[][] a = new int[m][10];
        for (int k = 0; k < n; k++) {
            int x = nums[k];
            for (int i = 0; x > 0; x /= 10, i++) {
                // 先更新答案, 再记录次数
                ans += k - a[i][x % 10]++;
            }
        }

        return ans;
    }

}
