package com.oycm.algorithm.i.permutation;

public class Solution_7 {

    /**
     * 1718. <a href="https://leetcode.cn/problems/construct-the-lexicographically-largest-valid-sequence/description/">构建字典序最大的可行序列</a> 2080
     *
     * @param n [1, 20]
     * @return 返回满足条件中 字典序最大 的序列
     */
    public int[] constructDistancedSequence(int n) {
        /*
        整数 n ，请你找到满足下面条件的一个序列
            整数 1 在序列中只出现一次。
            2 到 n 之间每个整数都恰好出现两次。
            对于每个 2 到 n 之间的整数 i ，两个 i 之间出现的距离恰好为 i 。
        序列里面两个数 a[i] 和 a[j] 之间的 距离 ，我们定义为它们下标绝对值之差 |j - i| 。
         */
        /*
        构造这个答案，每次选最大的数
         */
        int[] ans = new int[2 * n - 1];

        return ans;
    }

}
