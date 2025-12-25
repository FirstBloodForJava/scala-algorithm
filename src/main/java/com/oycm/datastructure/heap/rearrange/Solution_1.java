package com.oycm.datastructure.heap.rearrange;


public class Solution_1 {

    /**
     * 984. <a href="https://leetcode.cn/problems/string-without-aaa-or-bbb/description/">不含 AAA 或 BBB 的字符串</a> 1474
     * <p>
     * 给定两个整数 a 和 b ，返回 任意 字符串 s ，要求满足:
     * s 的长度为 a + b，且正好包含 a 个 'a' 字母与 b 个 'b' 字母；
     * 子串 'aaa' 没有出现在 s 中；
     * 子串 'bbb' 没有出现在 s 中。
     *
     * @param a
     * @param b
     * @return
     */
    public String strWithout3a3b(int a, int b) {
        /*
        a / 2, a / 1
        b / 2, b / 1
        假设 b 是较小数, b <= a <= 2(b+1) 一定能输出符合要求的字符串
        a > b; a - b - 1, 是前面需要多填 a 的数量
         */
        StringBuilder ans = new StringBuilder();
        if (a > b) {
            while (a > 0) {
                if (a - b - 1 > 0) {
                    ans.append("aa");
                    a -= 2;
                } else {
                    ans.append("a");
                    a--;
                }
                if (b > 0) {
                    ans.append("b");
                    b--;
                }
            }
        } else {
            while (b > 0) {
                if (b - a - 1 > 0) {
                    ans.append("bb");
                    b -= 2;
                } else {
                    ans.append("b");
                    b--;
                }
                if (a > 0) {
                    ans.append("a");
                    a--;
                }
            }
        }

        return ans.toString();
    }
}
