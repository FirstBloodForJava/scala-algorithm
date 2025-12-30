package com.oycm.datastructure.stack.parse;


public class Solution_4 {

    /**
     * 8. <a href="https://leetcode.cn/problems/string-to-integer-atoi/description/">字符串转换整数 (atoi)</a>
     *
     * @param s
     * @return 将字符串解析成 32 位有符号整数
     */
    public static int myAtoi(String s) {
        /*
        读取规则:
            空格：读入字符串并丢弃无用的前导空格（" "）.
            符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
            转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
            舍入：超过 [−2^31,  2^31 − 1] 范围, 小于 −2^31 结果为 -2^31, 大于 2^31 − 1, 结果为 2^31 − 1
         */
        int ans = 0;
        int n = s.length(), i = 0;
        char[] cs = s.toCharArray();
        // 忽略前置空格
        while (i < n && cs[i] == ' ') {
            i++;
        }

        int sign = 1;
        if (i < n && (cs[i] == '+' || cs[i] == '-')) {
            sign = cs[i] == '+' ? 1 : -1;
            i++;
        }
        for (; i < n && Character.isDigit(cs[i]); i++) {
            // 题解 拆分 ans * 10 - d > Integer.MAX_VALUE 这个不等式, 避免 乘法 和 加法溢出
            int d = cs[i] - '0';
            if (ans > Integer.MAX_VALUE / 10 || ans * 10 > Integer.MAX_VALUE - d) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = ans * 10 + d;
        }

        return  ans * sign;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println((int) (1L << 31));

        myAtoi("2147483648");
    }
}
