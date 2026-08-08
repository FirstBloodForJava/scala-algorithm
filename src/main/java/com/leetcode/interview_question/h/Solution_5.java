package com.leetcode.interview_question.h;

public class Solution_5 {

    /**
     * 面试题 08.05. <a href="https://leetcode.cn/problems/recursive-mulitply-lcci/description/">递归乘法</a>
     *
     * @param A
     * @param B
     * @return
     */
    public int multiply(int A, int B) {
        /*
        递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
         */
        /*
        5 * 8
        5 转换成 2 进制为 101 = 4 + 1
        5 * 8 = (4 + 1) * 8 =>
        和快速幂相似，初始值为 8，如果最低位为 1 则假设 B
         */
        int ans = 0;
        while (A > 0) {
            if ((A & 1) == 1) {
                ans += B;
            }
            B += B;
            A >>= 1;
        }

        return ans;
    }

}
