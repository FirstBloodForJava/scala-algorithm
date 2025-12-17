package com.oycm.algorithm.h.xor;

import java.util.HashMap;
import java.util.Map;

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

    public static int hashOptimize(int[] arr) {
        /*
        因为 i < j <= k, 其实这里就是二元组问题
        [l, r] 表示连续子数组的异或和 [0,1] ^ [0,3] == [2,3]
        s 记为 arr 的异或前缀和
        a = s[i] ^ s[j]
        b = s[j] ^ s[k+1]
        a == b => s[i] ^ s[j] == s[j] ^ s[k+1], a == b, a ^ i == b ^ i, 可以两边同时 ^ s[j]
        即 s[i] == s[k+1] 时, 该区间的答案就是 k - i
        枚举右(i+1), 维护左(i) 可以省略 前缀异或和 的创建过程
        // todo 使用 hash 表优化

        如果 相同的异或前缀和 xor = s[i1] = s[i2] ...
        s[k+1] ^ xor == 0, 则三元组有 (k - i1) + (k - i2) + ... = k * m + (i1 + i2 + ...) m 表示 相同异或和的个数， i1 + i2 + ... 表示相同异或和的下标

        */
        int ans = 0;
        int n = arr.length;
        // 相同个数和
        Map<Integer, Integer> cnt = new HashMap<>();
        // 相同下标和
        Map<Integer, Integer> total = new HashMap<>();

        /*for (int i = 0; i <= n; i++) {
            int xor = s[i];
            ans += cnt.getOrDefault(xor, 0) * (i - 1) - total.getOrDefault(xor, 0);

            cnt.merge(xor, 1, Integer::sum);
            total.merge(xor, i, Integer::sum);
        }*/
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur ^= arr[i];
            if (cnt.containsKey(cur)) {
                ans += cnt.get(cur) * i - total.get(cur);
            }
            int pre = cur ^ arr[i];
            cnt.merge(pre, 1, Integer::sum);
            total.merge(pre, i, Integer::sum);
        }

        return ans;
    }

    public static void main(String[] args) {
        hashOptimize(new int[]{2, 3, 1, 6, 7});
    }
}
