package com.oycm.hot100.trick;

public class Solution_96 {

    /**
     * 136. <a href="https://leetcode.cn/problems/single-number/description/">只出现一次的数字</a>
     *
     * @param nums nums 中其他元素都出现了 2 次
     * @return 找出只出现一次数字的值
     */
    public int singleNumber(int[] nums) {
        /*
        给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
         */
        /*
        利用 a ^ a = 0，a ^ 0 = a 异或运算性质
         */
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }

        return ans;
    }

}
