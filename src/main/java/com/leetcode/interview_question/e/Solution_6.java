package com.leetcode.interview_question.e;

public class Solution_6 {

    /**
     * 面试题 05.06. <a href="https://leetcode.cn/problems/convert-integer-lcci/description/">整数转换</a>
     *
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        /*
        整数转换。编写一个函数，确定需要改变几个位才能将整数 A 转成整数 B。
         */
        return Integer.bitCount(A ^ B);
    }

}
