package com.leetcode.interview_question.h;

public class Solution_1 {

    /**
     * 面试题 08.01. <a href="https://leetcode.cn/problems/three-steps-problem-lcci/description/">三步问题</a>
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        /*
        三步问题。
        有个小孩正在上楼梯，楼梯有 n 阶台阶，小孩一次可以上 1 阶、2 阶或 3 阶。
        实现一种方法，计算小孩有多少种上楼梯的方式。
        结果可能很大，你需要对结果模 1000000007。
         */
        /*
        递推
        f[i] = f[i-1] + f[i-2] + f[i-3]
        边界 f[1] = 1, f[2] = 2, f[3] = 4;
         */
        if (n == 1) return 1;
        if (n == 2) return 2;

        int mod = 1000000007;
        long f1 = 1;
        long f2 = 2;
        long f3 = 4;
        for (int i = 4; i <= n; i++) {
            // 这样可能会溢出
            long temp = (f1 + f2 + f3) % mod;
            f1 = f2;
            f2 = f3;
            f3 = temp;
        }

        return (int) f3;
    }
}
