package com.oycm.month2026.july;

public class Solution_24 {

    /**
     * 3514. <a href="https://leetcode.cn/problems/number-of-unique-xor-triplets-ii/description/">不同 XOR 三元组的数目 II</a> 1884
     *
     * @param nums
     * @return
     */
    public int uniqueXorTriplets(int[] nums) {
        /*
        给你一个整数数组 nums 。
        XOR 三元组 定义为三个元素的异或值 nums[i] XOR nums[j] XOR nums[k]，其中 i <= j <= k。
        返回所有可能三元组 (i, j, k) 中 不同 的 XOR 值的数量。
         */
        /*
        1 <= nums.length <= 1500
        1 <= nums[i] <= 1500
         */
        /*
        1500 的二进制长度 11。
        题解思路：先用二次循环，算出所以两数异或的可能值，其最大值肯定不会超过 1 << length(max)，将范围中可能的数再和 nums 异或，就得到了 三数异或值
         */
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(mx));
        boolean[] has2 = new boolean[u];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                has2[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] has3 = new boolean[u];
        for (int i = 0; i < has2.length; i++) {
            if (!has2[i]) {
                continue;
            }
            for (int x : nums) {
                has3[x ^ i] = true;
            }
        }
        int ans = 0;
        for (boolean b : has3) {
            if (b) ans++;
        }
        return ans;
    }

}
