package com.leetcode.interview_question.e;

public class Solution_2 {

    /**
     * 面试题 05.02. <a href="https://leetcode.cn/problems/binary-number-to-string-lcci/description/">二进制数转字符串</a>
     *
     * @param num
     * @return
     */
    public String printBin(double num) {
        /*
        二进制数转字符串。给定一个介于 0 和 1 之间的实数（如 0.72），类型为 double，打印它的二进制表达式。
        如果该数字无法精确地用 32 位以内的二进制表示，则打印“ERROR”。
         */
        StringBuilder bin = new StringBuilder("0.");
        for (int i = 0; i < 6; ++i) { // 至多循环 6 次
            num *= 2;
            if (num < 1)
                bin.append('0');
            else {
                bin.append('1');
                if (--num == 0)
                    return bin.toString();
            }
        }
        return "ERROR";
    }
}
