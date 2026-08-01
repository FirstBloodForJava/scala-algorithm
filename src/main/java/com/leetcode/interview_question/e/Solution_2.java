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
        /*
        题解思路：
        十进制有限小数可以表示为一个形如 b / 10^k（b 为去掉小数点后的整数（忽略前导 0，包含末尾 0），k 为小数位数（包含末尾 0））；
        二进制有限小数可以表示为一个形如 b / 2^k 的最简分数，这里可以是最简分数，因为如果 b 是 2 的倍数，那么 k 就可以减少 1。
            b < 2^k，k >= 1，b 一定和 2 互质。
        对于一个十进制小数位有 6 位的 num，可以表示为分数 a / 10^k 或者 a / (2^6 * 5^6)。这里不是最简分数。可以规定：
            a 为去掉小数点后的整数（忽略前导 0，包含末尾 0）。
        如果 num 可以表示为有限位二进制小数，则有：
            a / (2^6 * 5^6) = b / 2^k
        两边同时乘以 2^k，得：
            a * 2^(k-6) / 5^6 = b
        由于 b 与 2 互质，等式左边不能有因子 2，所以 k-6 <= 0，即 k <= 6 时，且 a 是奇数时，取等号。
        由于 b < 2^k，所以当 num 十进制小数位最多只有 6 位时，若 num 能表示成有限二进制小数，则二进制有限小数位同样至多 6 位(k <= 6)。
        如果 num = b / 2^k 可以拆分有限二进制小数，那么可以拆分成 b1 / 2 + b2 / 2^2 + ... + b6 / 2^6
        num * 2
            如果大于等于 1，则当前二进制小数位为 1
            如果小于 1，则当前二进制小数位为 0
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
