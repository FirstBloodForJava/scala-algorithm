package com.oycm.hot100.binary_tree;

public class Solution_105 {

    /**
     * 96. <a href="https://leetcode.cn/problems/unique-binary-search-trees/description/">不同的二叉搜索树</a>
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        /*
        给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
         */
        /*
        假设 n 个节点存在二叉搜索树的个数是 G(n)，令 f(i) 为以 i 为根的二叉搜索数的个数，则
        G(n) = f(1) + f(2) + ... + f(n)
        当 i 为根节点，其左子树节点个数为 i-1 个，右子树节点个数为 n-i，则
        f(i) = G(i-1) * G(n-i)
        G(n) = G(0) * G(n-1) + G(1) * G(n-2) + ... + G(n-1) * G(0)
         */
        /*
        C0 = 1, C(n+1) = 2(2n+1)/(n+2) C(n)
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

}
