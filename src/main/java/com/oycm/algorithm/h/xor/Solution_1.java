package com.oycm.algorithm.h.xor;


public class Solution_1 {

    /**
     * 1486. <a href="https://leetcode.cn/problems/xor-operation-in-an-array/description/">数组异或操作</a> 1181
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        /*
        nums[i] = start + 2 * i, i in [0, n-1]
         */
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= start + 2 * i;
        }

        return xor;
    }

    public int answer(int n, int start) {
        /*
        start 是 偶数, start + 2*i 都是 偶数, nums[i] 的最低位都是 0, [0, n-1] 异或和 可以是所有 nums[i] 右移一位的异或和 再乘以2
            (nums[0] ^ nums[1] ... ^ nums[n-1]) = (nums[0]/2 ^ nums[1]/2 ^ ... ^ nums[n-1] /2) * 2
            = ((start/2 + 0) ^ (start/2 + 1) ... ^ (start/2 + n-1)) * 2

        start 是 奇数, start + 2*i 都是 奇数, nums[i] 的最低位都是 1, [0, n-1] 异或和 可以是所有 nums[i] 右移一位的异或和 再乘以2,
        同时最低位的以取决与 n 的奇偶性，n 是偶数，则结果是0，n 是奇数，则结果是1
            (nums[0] ^ nums[1] ... ^ nums[n-1]) = (nums[0]/2 ^ nums[1]/2 ^ ... ^ nums[n-1] /2) * 2 + (start & n & 1)
            = ((start/2 + 0) ^ (start/2 + 1) ... ^ (start/2 + n-1)) * 2 + (start & n & 1)

        上面 的两个式子可以合并, start 是偶数时, (start & n & 1) 结果为 0
        记 a = start/2, 上面的合并表达式为
        ((a + 0) ^ (a + 1) ... ^ (a + n-1)) * 2 + (start & n & 1)
        怎么求 (a + 0) ^ (a + 1) ... ^ (a + n-1) 表达式？ 可以利用异或运算的 归零率 1 ^ 1 = 0
        (0 ^ 1 ...^ a ^ (a+1) ...^ (a + n-1)) ^ (0 ^ 1 ... ^(a - 1))

        问题就转换成了求 0 - n 的异或和了
        当 x 是偶数时, x 和 x+1 只有最低位不同，所以 x ^ (x + 1) = 1
            0 ^ 1 = 1
            2 ^ 3 = 1
            4 ^ 5 = 1
            6 ^ 7 = 1
        因为 1 ^ 1 == 0, 所以从 0 开始, 每 4 个数的 异或和为 0, 0 ^ 1 ^ 2 ^ 3 == 0
        当 n = 4k + 3, 两个数一组，可以得到 (n + 1) / 2 = 2(k+1) 组, 偶数个 1, 例如 3,7 异或和为 0
        当 n = 4k + 1, 两个数一组, 可以得到 (n + 1) / 2 = 2k + 1 组, 奇数和 1, 例如 1,5 异或和为 1

        当 n = 4k + 2, 可以先计算 [0, 4k+1], 2k+1 组, 结果为 1, 再异或 4k+2, 结果为 1 ^ (n) = n + 1
        当 n = 4k + 4, 可以先计算 [0, 4k+3], 2(k+1)组, 结果为 0, 再异或 4k+4, 结果为 n

         */
        int a = start / 2;
        return (xor(a + n - 1) ^ xor(a - 1)) * 2 ^ (start & n & 1);
    }

    public int xor(int n) {
        int mod = n % 4;
        if (mod == 0) {
            return n;
        } else if (mod == 1) {
            return 1;
        } else if (mod == 2) {
            return n + 1;
        } else {
            return 0;
        }
    }
}
