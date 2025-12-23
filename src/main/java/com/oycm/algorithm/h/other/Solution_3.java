package com.oycm.algorithm.h.other;


public class Solution_3 {

    /**
     * 2965. <a href="https://leetcode.cn/problems/find-missing-and-repeated-values/description/">找出缺失和重复的数字</a> 1245
     *
     * @param grid nxn 的矩阵, 值 [1, n^2] 除了 a 出现了 两次, b 缺失之外, 每个整数都恰好出现一次
     * @return 求 重复的数组 a, 缺失的数字 b, ans[0] = a ，ans[1] = b
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        /*
        1 ^ 2 ^ 3 ... ^ n 的异或和 记为 xor1, 可以根据 n % 4
        当 n = 4k + 3, 两个数一组，可以得到 (n + 1) / 2 = 2(k+1) 组, 偶数个 1, 例如 3,7 异或和为 0
        当 n = 4k + 1, 两个数一组, 可以得到 (n + 1) / 2 = 2k + 1 组, 奇数和 1, 例如 1,5 异或和为 1

        当 n = 4k + 2, 可以先计算 [0, 4k+1], 2k+1 组, 结果为 1, 再异或 4k+2, 结果为 1 ^ (n) = n + 1
        当 n = 4k + 4, 可以先计算 [0, 4k+3], 2(k+1)组, 结果为 0, 再异或 4k+4, 结果为 n

        n^2 = (4k + 3)^2 = (16k^2 + 24k + 9) % 4 == 1, 结果为 1
        n^2 = (4k + 1)^2 = (16k^2 + 8k + 1) % 4 == 1, 结果为 1
        n^2 = (4k + 2)^2 = (16k^2 + 16k + 4) % 4 == 0, 结果为 n^2
        n^2 = (4k + 4)^2 = (16k^2 + 32k + 16) % 4 == 0, 结果为 n^2
        n 是 奇数时, 异或和 为 1
        n 时 偶数时，异或和 为 n^2
        grid 的异或和记为 xor2
        xor1 ^ xor2 = a ^ b 一边没有 a, b, 一边有 a, b
         */
        int n = grid.length;
        // a ^ b
        int xor = 0;
        for (int[] ints : grid) {
            for (int num : ints) {
                xor ^= num;
            }
        }
        xor ^= n % 2 > 0 ? 1 : n * n;
        int lowbit = Integer.numberOfTrailingZeros(xor);
        int[] ans = new int[2];
        /*
        [1, n^2] a 1 次, b 1次
        [1, n^2] a 2 次
        3 次相当于 1 次
         */
        for (int i = 0; i <= n * n; i++) {
            ans[i >> lowbit & 1] ^= i;
        }
        for (int[] ints : grid) {
            for (int num : ints) {
                ans[num >> lowbit & 1] ^= num;
            }
        }
        // ans[0, 1] 就是 a 或 b, 还需要确定哪个 是 a, 哪个是 b
        for (int[] ints : grid) {
            for (int num : ints) {
                if (num == ans[0]) {
                    return ans;
                }
            }
        }

        return new int[]{ans[1], ans[0]};
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(n & -n);
        System.out.println(Integer.numberOfTrailingZeros(n));
    }

}
