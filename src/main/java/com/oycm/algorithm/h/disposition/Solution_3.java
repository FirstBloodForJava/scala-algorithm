package com.oycm.algorithm.h.disposition;

public class Solution_3 {

    /**
     * 2425. <a href="https://leetcode.cn/problems/bitwise-xor-of-all-pairings/description/">所有数对的异或和</a> 1622
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int xorAllNums(int[] nums1, int[] nums2) {
        /*
        题解思路: 答案是求一大堆数字的异或和, 根据贡献法的思想, 可以考虑每个数字在这个一大堆中出现了多少次, 对答案的贡献是多少
        记 n = nums1.length, m = nums2.length;
        如果 n 是 偶数, 每个 nums2[i] 在 整个异或中出现 偶数次, 对答案的贡献是 0
        如果 m 是 偶数, 每个 nums1[i] 在 整个异或中出现 偶数次, 对答案的贡献是 0
         */
        int ans = 0;
        int n = nums1.length, m = nums2.length;
        if (n % 2 == 1) {
            for (int i = 0; i < m; i++) {
                ans ^= nums2[i];
            }
        }
        if (m % 2 == 1) {
            for (int i = 0; i < n; i++) {
                ans ^= nums1[i];
            }
        }
        return ans;
    }

}
