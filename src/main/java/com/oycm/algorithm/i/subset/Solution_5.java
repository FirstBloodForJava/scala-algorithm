package com.oycm.algorithm.i.subset;

public class Solution_5 {

    /**
     * 2044. <a href="https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets/description/">统计按位或能得到最大值的子集数目</a> 1568
     *
     * @param nums 整数数组
     * @return
     */
    public int countMaxOrSubsets(int[] nums) {
        /*
        找出 nums 子集 按位或 可能得到的 最大值
        选中的元素下标位置不一样，则认为两个子集 不同(不看元素, 看下标)
         */

        // 最大值
        int max = 0;
        for (int x : nums) {
            max |= x;
        }
        dfs(0, 0, nums, max);
        return cnt;
    }

    int cnt = 0;

    public void dfs(int i, int xor, int[] nums, int max) {
        if (i == nums.length) {
            if (xor == max) cnt++;
            return;
        }
        dfs(i + 1, xor, nums, max);
        // 作为参数玩下传, 不用恢复现场
        dfs(i + 1, xor | nums[i], nums, max);
    }

}

class Solution_5_BinaryEnum {

    public int countMaxOrSubsets(int[] nums) {
        /*
        可以先 或出最大值, 选/不选
        选完之后，怎么回溯这个 选中的值?
        用 二进制枚举
         */
        int n = nums.length, cnt = 0;
        // 最大值
        int max = 0;
        for (int x : nums) {
            max |= x;
        }
        for (int i = 0; i < (1 << n); i++) {
            int xor = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    // 选
                    xor |= nums[j];
                }
            }
            if (xor == max) cnt++;
        }

        return cnt;
    }
}
