package com.oycm.dualweek.lc2026.No190;

public class Solution_2 {

    /**
     * @param nums
     * @return
     */
    public int maxValidSplits(int[] nums) {
        /*
        给你一个整数数组 nums。
        你可以从 nums 中移除 至多一个 元素。记 arr 为按原始顺序保留其余元素后得到的数组，m 为其长度。
        如果 arr 的 分割位置 i 满足以下条件，则称其为 有效的 ：
            0 <= i < m - 1，且
            gcd(arr[0..i]) == gcd(arr[i + 1..m - 1])。
        长度为 1 的数组没有有效的分割位置。
        arr 的 得分 是其有效分割位置的数量。
        返回 arr 的 最大可能得分 。
        在这里，gcd(a) 表示数组 a 中所有元素的最大公约数。
         */
        /*
        枚举移除所有位置，前后缀分解，判断是否存在 gcd 相等的情况，取最大值
         */
        int ans = getScore(nums);
        int n = nums.length;
        int[] temp = new int[n - 1];
        for (int j = 0; j < n; j++) {
            // 枚举删除位置
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (i != j) {
                    temp[idx++] = nums[i];
                }
            }
            ans = Math.max(ans, getScore(temp));
        }

        return ans;
    }

    public int getScore(int[] nums) {
        int ans = 0;
        int n = nums.length;
        if (n <= 1) {
            return ans;
        }
        // 前缀 gcd
        int[] preGcd = new int[n];
        preGcd[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preGcd[i] = gcd(preGcd[i - 1], nums[i]);
        }
        // 后缀枚举
        int sufGcd = nums[n - 1];
        for (int i = n - 1; i > 0; i--) {
            sufGcd = gcd(sufGcd, nums[i]);
            if (preGcd[i - 1] == sufGcd) {
                ans++;
            }

        }

        return ans;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


}
