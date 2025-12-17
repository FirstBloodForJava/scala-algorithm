package com.oycm.algorithm.h.andor;


public class Solution_1 {

    /**
     * 2980. <a href="https://leetcode.cn/problems/check-if-bitwise-or-has-trailing-zeros/description/">检查按位或是否存在尾随零</a> 1234
     * <p>
     * 数组中 选出 两个或更多 元素 进行 or 运算，结果的二进制表示中 至少 存在一个尾随零
     *
     * @param nums
     * @return 如果存在两个或更多元素 进行 or 运算，结果存在尾随零，返回 true，否则 false
     */
    public boolean hasTrailingZeros(int[] nums) {
        /*
        nums 至少存在 2 个数 的最低位 是 非 1，是偶数
         */
        int cnt = nums.length;
        for (int num : nums) {
            cnt -= num % 2;
        }

        return cnt >= 2;
    }
}
