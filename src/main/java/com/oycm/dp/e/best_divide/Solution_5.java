package com.oycm.dp.e.best_divide;

public class Solution_5 {

    /**
     * 91. <a href="https://leetcode.cn/problems/decode-ways/description/">解码方法</a>
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        /*
        一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
            "1" -> 'A'
            "2" -> 'B'
            ...
            "25" -> 'Y'
            "26" -> 'Z'
        然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
        例如，"11106" 可以映射为：
        "AAJF" ，将消息分组为 (1, 1, 10, 6)
        "KJF" ，将消息分组为 (11, 10, 6)
        消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
        注意，可能存在无法解码的字符串。
        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
        题目数据保证答案肯定是一个 32 位 的整数。
         */
        /*
        如果 s 包含两个 00，则一定无法解码，或者前导 0 开始
        dfs(i) 表示 s[0 : i] 的解码总数
        dfs(i) 可以通过以下方式计算
            s[i] != '0', 子问题 dfs(i-1) 答案
            i > 0 && s[i-1] = '1', 子问题 dfs(i-2) 答案
            i > 0 && s[i-1] = '2' && s[i] <= '6', 子问题 dfs(i-2)
        dfs(i) 等于上诉条件累加结果
        递归边界 dfs(-1) = 1
         */
        if (s.startsWith("0")) {
            return 0;
        }
        int f0 = 1, f1 = 1;
        for (int i = 1; i < s.length(); i++) {
            int f = 0;
            if (s.charAt(i) != '0') {
                // 当个分割
                f += f1;
            }
            if (s.charAt(i - 1) == '1') {
                f += f0;
            } else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                f += f0;
            }

            f0 = f1;
            f1 = f;
        }
        return f1;
    }

}
