package com.oycm.month2026.november;

public class Solution_1 {

    /**
     * 453. <a href="https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/description/">最小操作次数使数组元素相等</a>
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        /*
        给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
         */
        /*
        题解思路：等价转换
        [2, 2, 2, 3] => [3, 3, 3, 3] 可以转换成：一个数减少 1，然后整个数组所有元素再加 1。
        由于没有要求相等的数是多少，那么可以去掉加 1 操作，所有数减少到相同的最少操作数。
        只能减少，减少到最大的数一定是 min(nums) nums 数组的最小值
        sum(nums) - n * min(nums)
         */
        int mn = Integer.MAX_VALUE;
        long sum = 0;
        for (int num : nums) {
            mn = Math.min(mn, num);
            sum += num;
        }
        return (int) (sum - mn * nums.length);
    }

}
