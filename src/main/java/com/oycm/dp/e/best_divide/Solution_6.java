package com.oycm.dp.e.best_divide;

public class Solution_6 {

    /**
     * 639. <a href="https://leetcode.cn/problems/decode-ways-ii/description/">解码方法 II</a>
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        /*
        一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
            'A' -> "1"
            'B' -> "2"
            ...
            'Z' -> "26"
        要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
            "AAJF" 对应分组 (1 1 10 6)
            "KJF" 对应分组 (11 10 6)
        注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
        除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。
        例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。
        对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
        给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
        由于答案数目可能非常大，返回 1^9 + 7 的 模 。
         */
        /*
        f[i][j] 表示 s[0:i] 字符串，s[j] 是 j 的解码数，j = 11，10 表示总合
         */
        char c0 = s.charAt(0);
        if (c0 == '0') {
            return 0;
        }
        int mod = 1000000007;
        long[] f0 = new long[11];
        long[] f1 = new long[11];
        f0[10] = 1;
        if (c0 == '*') {
            for (int i = 1; i < 10; i++) {
                f1[i] = 1;
            }
            f1[10] = 9;
        } else {
            f1[c0 - '0'] = 1;
            f1[10] = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            long[] f = new long[11];
            char c = s.charAt(i);
            if (c == '*') {
                long sum = 0;
                for (int j = 1; j < 10; j++) {
                    // 单个分割
                    f[j] = f1[10];
                    if (f1[1] > 0) {
                        f[j] += f0[10];
                    }
                    if (j <= 6) {
                        if (f1[2] > 0) {
                            f[j] += f0[10];
                        }

                    }
                    f[j] = f[j] % mod;
                    sum += f[j];
                }
                f[10] = sum % mod;

            } else if (c == '0') {
                f[0] = (f1[1] > 0 ? f0[10] : 0) + (f1[2] > 0 ? f0[10] : 0);
                f[10] = f[0] % mod;
            } else if (c <= '6') {
                // 单独分割
                f[c - '0'] = f1[10] + (f1[1] > 0 ? f0[10] : 0) + (f1[2] > 0 ? f0[10] : 0);
                f[10] = f[c - '0'] % mod;
            } else {
                f[c - '0'] = f1[10] + (f1[1] > 0 ? f0[10] : 0);
                f[10] = f[c - '0'] % mod;
            }
            f0 = f1;
            f1 = f;
        }


        return (int) f1[10];
    }

    public static int numDecodings2(String s) {
        /*
        通过固定最后一次解码计算 f[i]
        f[i] 表示 计算 [0, i] 字符串的方案数，可以根据 s[i], s[i-1] 和已经计算过的 f[i-1], f[i-2] 计算 f[i]
         */
        char c0 = s.charAt(0);
        if (c0 == '0') {
            return 0;
        }
        int mod = 1000000007;
        long f0 = 1, f1 = c0 == '*' ? 9 : 1;
        for (int i = 1; i < s.length(); i++) {
            long f = 0;
            char c = s.charAt(i);
            char pc = s.charAt(i - 1);
            // [0, i-1] (f1) * s[i] (可选方案)
            if (c == '*') {
                f = f1 * 9 % mod;
            } else if (c != '0') {
                f = f1;
            }
            // [0, i-2] (f0) * s[i-1, i]
            if (c == '*' && pc == '*') {
                // ** 15 种方案
                f = (f + f0 * 15) % mod;
            } else if (pc == '*') {
                // *? ? 为 1 => 2; 2 => 1
                f = (f + f0 * (c <= '6' ? 2 : 1)) % mod;
            } else if (c == '*') {
                if (pc == '1') {
                    f = (f + f0 * 9) % mod;
                } else if (pc == '2') {
                    f = (f + f0 * 6) % mod;
                }
            } else if (pc == '1') {
                f = (f + f0) % mod;
            } else if (pc == '2' && c <= '6') {
                f = (f + f0) % mod;
            }
            f0 = f1;
            f1 = f;
        }

        return (int) f1;
    }

}
