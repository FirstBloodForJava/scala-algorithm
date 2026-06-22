package com.oycm.week.lc2024.No417;

public class Solution_4 {

    /**
     * 3307. <a href="https://leetcode.cn/problems/find-the-k-th-character-in-string-game-ii/description/">找出第 K 个字符 II</a> 2232
     *
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacter(long k, int[] operations) {
        /*
        Alice 和 Bob 正在玩一个游戏。最初，Alice 有一个字符串 word = "a"。
        给定一个正整数 k 和一个整数数组 operations，其中 operations[i] 表示第 i 次操作的类型。
        现在 Bob 将要求 Alice 按顺序执行 所有 操作：
            如果 operations[i] == 0，将 word 的一份 副本追加 到它自身。
            如果 operations[i] == 1，将 word 中的每个字符 更改 为英文字母表中的 下一个 字符来生成一个新字符串，并将其 追加 到原始的 word。
        在执行所有操作后，返回 word 中第 k 个字符的值。
         */
        /*
        n = operations.length, 最终操作后的长度为 1 << n
        题解思路：分类讨论
        操作 n 次后，字符串的长度为 1 << n
            如果 k <= 1 << (n-1), 那么第 k 个字母在 S 的左半段，不会受到 operations[n-1] 影响，问题等价去掉 operations[n-1] 的子问题
            如果 k > 1 << (n-1), 那么第 k 个字母在 S 的右半段，问题等价于去掉 operation[n-1]，计算右半段第 k - 1 << (n-1) 个字母的子问题。
            如果 operations[n-1] = 1，那么子问题返回的字母需要加 1，否则不变
         */
        /*
        迭代实现，计算 k > 2^i 需要增加的次数
         */
        int cnt = 0;
        /*for (int i = 63 - Long.numberOfLeadingZeros(k - 1); i >= 0; i--) {
            if (k > 1L << i) {
                cnt += operations[i];
                k -= 1L << i;
            }
        }*/
        k--;
        for (int i = 63 - Long.numberOfLeadingZeros(k); i >=0 ; i--) {
            cnt += k >> i & operations[i];
        }

        return (char) ('a' + cnt % 26);
    }

}
