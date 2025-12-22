package com.oycm.algorithm.h.mind;


public class Solution_2 {

    /**
     * 1558. <a href="https://leetcode.cn/problems/minimum-numbers-of-function-calls-to-make-target-array/description/">得到目标数组的最少函数调用次数</a> 1637
     *
     * @param nums
     * @return
     */
    public static int minOperations(int[] nums) {
        /*
        nums[i] > 0 则至少需要 执行一次 + 1
        最大的 nums[i] 至少需要执行 最高次位 op = 1 的操作，中间低于此值的数，就不需要执行 op = 1 的操作了，只需要执行后续的 加法操作
        前面没有考虑到另外一种比较快的方式
        1, << 1, +1, << 1 ... 无法右移的 加1 的次数, 其中这 里面 << 的次数是可以公用的, +1 次数需要单独计算
        1000000000 == 111011100110101100101000000000 低于 最高位的 1, 都可以通过 op=0 操作获得, 最高位的 1 只能通过公共的 op=1 操作获得，最多执行最高位次
         */
        // 记录初始化加法次数
        int ans = 0;
        // 记录需要执行的 op=1 操作次数
        int op1 = 0;
        for (int num : nums) {
            if (num > 0) {
                ans += Integer.bitCount(num);
                op1 = Math.max(op1, 31 - Integer.numberOfLeadingZeros(num));

            }

        }

        return ans + op1;
    }

    private void modify(int[] nums, int op, int idx) {
        if (op == 0) {
            nums[idx] = nums[idx] + 1;
        }
        if (op == 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums[i] * 2;
            }
        }
    }

    public static void main(String[] args) {
        //
        int i = 1000000000;
        int j = 1;
        int ans = 1;
        while ((j << 1) <= i) {
            j <<= 1;
            j++;
            ans += 2;
        }
        System.out.println(ans);
    }
}
