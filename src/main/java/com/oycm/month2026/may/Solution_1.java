package com.oycm.month2026.may;

public class Solution_1 {

    /**
     * 396. <a href="https://leetcode.cn/problems/rotate-function/description/">旋转函数</a>
     *
     * @param nums 长度为 n 的整数数组 , n [1, 1e5]
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        /*
        假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
            F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
            arr1 = nums 是循环数组，向前移动一步
         */
        /*
        设 n = nums.length = 4
        F(0) = 0 * nums[0] + 1 * nums[1] + 2 * nums[2] + 3 * nums[3]
        F(1) = 0 * nums[3] + 1 * nums[0] + 2 * nums[1] + 3 * nums[2]
        观察组成变化：
            0, 1, 2 往右移动，都增加了 nums[i]
            3 移动到前面，减少了 3 * nums[3]
        F(1) = F[0] + nums[0] + nums[1] + nums[2] - 3 * nums[3]
             = F[0] + nums[0] + nums[1] + nums[2] + nums[3] - 4 * nums[3]
             = F[1] + S + 4 * nums[3]
        S 表示数组和
        F(2) = 0 * nums[2] + 1 * nums[3] + 2 * nums[0] + 3 * nums[1]
             = F(1) + nums[3] + nums[0] + nums[1] - 3 * nums[2]
             = F(1) + S - 4 * nums[2]
        先初始化 F[0]
        F[i] = F[i-1] + S - n * nums[n-i]
         */
        int n = nums.length;
        int f = 0;
        int s = 0;
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
            s += nums[i];
        }
        int ans = f;
        for (int i = 1; i < n; i++) {
            f += s - n * nums[n - i];
            ans = Math.max(ans, f);
        }

        return ans;
    }

}


