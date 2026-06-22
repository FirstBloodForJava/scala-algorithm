package com.oycm.week.lc2024.No390;

public class Solution_2 {

    /**
     * 3091. <a href="https://leetcode.cn/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/description/">执行操作使数据元素之和大于等于 K</a> 1522
     *
     * @param k
     * @return
     */
    public int minOperations(int k) {
        /*
        给你一个正整数 k 。最初，你有一个数组 nums = [1] 。
        你可以对数组执行以下 任意 操作 任意 次数（可能为零）：
            选择数组中的任何一个元素，然后将它的值 增加 1 。
            复制数组中的任何一个元素，然后将它附加到数组的末尾。
            返回使得最终数组元素之 和 大于或等于 k 所需的 最少 操作次数。
         */
        /*
        最大值 和 增加量相等，每次操作都只能对数组和增加 1，但是对最大值增加 1 是最优的，如果 下一次数组和还小于 k，
        则将新的最大值复制比增加 1 更优，将最大值变大后，复制操作，等于执行了前面的 k 次增加操作。
        假设执行了 x 操作，使得数组元素之和 >= k
        最优解应该是执行 y 次增加，再执行 x-y 复制
        (1 + y) * (x - y + 1) >= k
        x - y + 1 + xy - y^2 + y >= k
        -y^2 + xy + x >= k - 1
        执行 x 次增加，y 次复制，满足 (x + 1) * (1 + y) >= k x+y 的最小值
         */
        int sqrt = (int) Math.sqrt(k);
        return Math.min(sqrt - 1 + (k - 1) / sqrt, sqrt + (k - 1)/ (sqrt + 1));
    }

}
