package com.oycm.hot100.ordinary_array;

public class Solution_16 {

    /**
     * 238. <a href="https://leetcode.cn/problems/product-of-array-except-self/description/">除了自身以外数组的乘积</a>
     *
     * @param nums 整数数组
     * @return 返回 数组 answer
     */
    public int[] productExceptSelf(int[] nums) {
        /*
        answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
         */
        /*
        如果使用除法，nums 所有乘积用 prod 表示，answer[i] = prod / nums[i]
        preProd[i] 表示 nums [0, i) 前 i 个元素乘积
        sufProd[i] 表示 nums (i, n-1] 后 n-1-i 个元素乘积
        answer[i] = preProd[i] * sufProd[i];
        用 answer 数组表示 nums 后缀乘积
        用一个变量表示 preProd[i]，因为每次计算只会使用前一次的 preProd[i]
         */
        int n = nums.length;
        int[] answer = new int[n];
        answer[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            answer[i] = answer[i + 1] * nums[i + 1];
        }
        int preProd = 1;
        for (int i = 0; i < n; i++) {
            answer[i] *= preProd;
            preProd *= nums[i];
        }

        return answer;
    }

}
