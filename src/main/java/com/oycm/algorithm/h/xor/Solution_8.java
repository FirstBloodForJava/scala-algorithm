package com.oycm.algorithm.h.xor;

public class Solution_8 {

    /**
     * 1442. <a href="https://leetcode.cn/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/">形成两个异或相等数组的三元组数目</a> 1525
     * <p>
     * 0 <= i < j <= k < arr.length
     * <p>
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1];
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     *
     * @param arr
     * @return 求 a == b 的三元组数目
     */
    public int countTriplets(int[] arr) {
        /*
        arr[i] ^ arr[i+1] ^ ... arr[k] == 0 的组合数
        0 <= i < j <= k
        如果 [0, k] 异或和 == 0, k > 0, 方案数是 k
         */
        // 暴力做法 O(n^2)
        int ans = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int xor = 0;
            for (int j = i; j < n; j++) {
                xor ^= arr[j];
                if (xor == 0) {
                    ans += j - i;
                }
            }
        }

        return ans;
    }

    public int hashOptimize(int[] arr) {
        /*
        因为 i < j <= k, 其实这里就是二元组问题
        [] 表示连续子数组的异或和 [0,1] ^ [0,3] == [2,3]
        s 记为 arr 的异或前缀和
        a = s[i] ^ s[j]
        b = s[j] ^ s[k+1]
        a == b => s[i] ^ s[j] == s[j] ^ s[k+1]
        即 s[i] == s[k+1] 时, 该区间的答案就是 k - i
        // todo 使用 hash 表优化
         */

        return 0;
    }
}
